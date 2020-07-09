package com.skan.farm.router;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.*;

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
class RouterCattleManagementTest {

    @Test
    void routerFunctionGet() {
        WebTestClient.bindToServer()
                .baseUrl("http://localhost:8000")
                .build()
                .get().uri("/cattle/cattle_management_list")
                .exchange()
                .expectStatus().isOk();

    }
    @Test
    void routerFunctionPost() {


        WebTestClient.bindToServer()
                .baseUrl("http://localhost:8000")
                .build()
                .post()
                .attribute("","")
                .attribute("","")
                .exchange()
                .expectStatus().isOk();
    }
}