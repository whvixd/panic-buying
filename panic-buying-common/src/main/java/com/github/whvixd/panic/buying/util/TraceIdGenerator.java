package com.github.whvixd.panic.buying.util;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by wangzhixiang on 2021/04/26.
 */
public class TraceIdGenerator {
    private static final String[] PATCH = {"0", "00", "000", "0000", "00000", "000000", "0000000"};

    private static final String IP_FACTOR = patch(getIpFactor(), 8);
    private static final String DEFUALT_IP = "127.0.0.1";

    // month + day + pid
    private static final String P_INFO = getProcessInfo();

    private static final int MAX_INCREASEMENT = 0xffffff;

    private static final AtomicInteger increase = new AtomicInteger(0);

    // 两位的后缀
    private static final String SUB_FIX = "20";


    private static String ip2long(String ip) {
        String ret;
        String ipGroups[] = ip.split("\\.");
        Integer[] ipInt = new Integer[4];
        for (int i = 0; i < ipGroups.length; i++) {
            ipInt[i] = Integer.valueOf(ipGroups[i]);
        }
        ret = Integer.toHexString(ipInt[0]) + Integer.toHexString(ipInt[1])
                + Integer.toHexString(ipInt[2]) + Integer.toHexString(ipInt[3]);
        return ret;
    }

    /**
     * 获取启动进程信息
     */
    private static String getProcessInfo() {
        RuntimeMXBean rmx = ManagementFactory.getRuntimeMXBean();
        String name = rmx.getName();
        String pid = patch(Integer.toHexString(Integer.valueOf(name.replaceAll("@.*$", ""))), 4);
        long startTime = rmx.getStartTime() % 1000;
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(startTime);
        String month = patch(Integer.toHexString(c.get(Calendar.MONTH)), 2);
        String day = patch(Integer.toHexString(c.get(Calendar.DAY_OF_MONTH)), 2);
        return month + day + pid;
    }

    private static String timeFactor() {
        Date data = new Date();
        long msec = data.getTime() / 1000;
        return Long.toHexString(msec);
    }

    private static String increaseFactor() {
        int result = 0;
        for (; ; ) {
            int current = increase.get();
            int next = current + 1;
            if (next > MAX_INCREASEMENT) {
                next = 1;
            }
            if (increase.compareAndSet(current, next)) {
                result = next;
                break;
            }
        }
        return Integer.toHexString(result);
    }

    private static String patch(String hex, int length) {
        if (hex.length() >= length) {
            return hex;
        } else {
            int gap = length - hex.length();
            return PATCH[(gap > 7 ? 7 : gap) - 1] + hex;
        }
    }

    private static List<String> getLocalIpList() {
        List<String> ipList = new ArrayList<>();
        try {
            Enumeration<NetworkInterface> networkInterfaces =
                    NetworkInterface.getNetworkInterfaces();
            NetworkInterface networkInterface;
            Enumeration<InetAddress> inetAddresses;
            InetAddress inetAddress;
            String ip;
            while (networkInterfaces.hasMoreElements()) {
                networkInterface = networkInterfaces.nextElement();
                inetAddresses = networkInterface.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    inetAddress = inetAddresses.nextElement();
                    if (inetAddress != null && inetAddress instanceof Inet4Address) { // IPV4
                        ip = inetAddress.getHostAddress();
                        ipList.add(ip);
                    }
                }
            }
        } catch (SocketException e) {
        }
        return ipList;
    }

    private static String getIpFactor() {
        List<String> ips = getLocalIpList();
        String ipAddr = DEFUALT_IP;
        if (ips == null) {
            return ip2long(ipAddr);
        }
        for (String ipTmp : ips) {
            if (!DEFUALT_IP.equals(ipTmp)) {
                ipAddr = ipTmp;
                break;
            }
        }
        return ip2long(ipAddr);
    }

    public static String gen() {
        String timeFactor = patch(timeFactor(), 8);
        String increaseFactor = patch(increaseFactor(), 6);
        return IP_FACTOR + timeFactor + P_INFO + increaseFactor + SUB_FIX;
    }
}
