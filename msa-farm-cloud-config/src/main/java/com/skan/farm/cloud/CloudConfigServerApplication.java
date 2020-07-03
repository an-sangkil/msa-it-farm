package com.skan.farm.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

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
@SpringBootApplication
@EnableConfigServer
public class CloudConfigServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudConfigServerApplication.class);
    }
}
