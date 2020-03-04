package com.github.whvixd.panic.buying.biz.controller;

import com.github.whvixd.PanicBuyingBizApplication;
import com.github.whvixd.panic.buying.biz.BaseTest;
import com.github.whvixd.panic.buying.model.Result;
import com.github.whvixd.panic.buying.model.SaleOrderVO;
import com.github.whvixd.panic.buying.util.FastJsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * Created by wangzhx on 2020/3/2.
 */
@Slf4j
@SpringBootTest(classes = PanicBuyingBizApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SaleOrderControllerTest extends BaseTest {


    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void createTest() {
        String url = "http://localhost:8080/sale/order/create";
        SaleOrderVO.Arg arg = new SaleOrderVO.Arg();
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("arg", arg);
        Result result = testRestTemplate.postForObject(url, params, Result.class);
        log.info("resultResponseEntity:{}", FastJsonUtil.toJsonWithNull(result));
    }
}
