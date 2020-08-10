package com.github.whvixd.panic.buying.biz.controller;

import com.github.whvixd.panic.buying.biz.BaseTest;
import com.github.whvixd.panic.buying.controller.SaleOrderController;
import com.github.whvixd.panic.buying.model.CreateSaleOrderVO;
import com.github.whvixd.panic.buying.util.FastJsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Created by wangzhx on 2020/3/2.
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class SaleOrderControllerTest extends BaseTest {

    @Autowired
    private SaleOrderController saleOrderController;

    private MockMvc mockMvc;

    @Rule
    public ContiPerfRule contiPerfRule = new ContiPerfRule();

    @Before
    public void setupMockMvc() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(saleOrderController).build();
    }

    @Test
    @PerfTest(invocations = 200, threads = 20)
    public void restCreateTest() throws Exception {
        CreateSaleOrderVO.Arg arg = new CreateSaleOrderVO.Arg();
        arg.setPayAmount(1000L);
        arg.setUserId("1000");
        arg.setTotalDuration(1);
        arg.setProductId("1");

        mockMvc.perform(MockMvcRequestBuilders.post("/sale/order/create")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(FastJsonUtil.toJson(arg)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print());
    }
}
