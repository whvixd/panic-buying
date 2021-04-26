package com.github.whvixd.panic.buying.aspect;

import com.github.whvixd.panic.buying.util.TraceIdGenerator;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by wangzhixiang on 2021/04/26.
 */
public class AccessFilter implements Filter {
    private final static String TRACE_ID_KEY = "traceId";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String traceId = request.getHeader(TRACE_ID_KEY);
        if (StringUtils.isBlank(traceId)) {
            MDC.put(TRACE_ID_KEY, TraceIdGenerator.gen());
        }
        filterChain.doFilter(servletRequest, servletResponse);
        MDC.clear();
    }
}
