package com.skan.farm.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 * Description :
 *
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2020 by CJENM|Mezzomedia. All right reserved.
 * @since 2020-06-04
 */
@RestController
public class TestRestController {

    @Value("${google.id}")
    private String googleId;

    @GetMapping("get")
    public String testCloudConfig() {
        return googleId;
    }
}

