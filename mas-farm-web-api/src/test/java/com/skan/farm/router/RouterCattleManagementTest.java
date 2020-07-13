package com.skan.farm.router;

import com.skan.farm.repository.TestNonConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * <pre>
 * Description :
 *
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2020 by CJENM|Mezzomedia. All right reserved.
 * @since 2020-07-09
 */
@TestNonConfiguration
@AutoConfigureMockMvc
//@WebMvcTest(RouterCattleManagement.class)// -> 가볍게 컨트롤러만 테스트할때 사용
class RouterCattleManagementTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    private WebApplicationContext ctx;

    @BeforeEach
    public void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(ctx).addFilters(new CharacterEncodingFilter("UTF-8", true)) // 필터 추가
                .alwaysDo(MockMvcResultHandlers.print()).build();
    }


    @Test
    void routerFunctionGet() throws Exception {
       /* WebTestClient.bindToServer()
                .baseUrl("http://localhost:8000")
                .build()
                .get().uri("/cattle/cattle_management_list")
                .exchange()
                .expectStatus().isOk();*/

        //mvc.perform(MockMvcRequestBuilders.get("/cattle/cattle_management_list?page=0&size=10").accept(MediaType.ALL))
        mvc.perform(MockMvcRequestBuilders.get("/cattle/cattle_management_list?page=0&size=10&entity_management_id=1111&identity_management_id=2222").accept(MediaType.ALL))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
        ;


    }

    @Test
    void routerFunctionPost() {


       /* WebTestClient.bindToServer()
                .baseUrl("http://localhost:8000")
                .build()
                .post()
                .attribute("","")
                .attribute("","")
                .exchange()
                .expectStatus().isOk();*/
    }
}