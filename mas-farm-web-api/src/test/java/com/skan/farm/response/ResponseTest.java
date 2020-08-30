package com.skan.farm.response;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <pre>
 * Description :
 *
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2020 by SKAN . All right reserved.
 * @since 2020-08-29
 */
@Slf4j
class ResponseTest {

    @Test
    void OK() {
        log.debug("response = {} ", new Response<>().OK("test data"));
    }
}