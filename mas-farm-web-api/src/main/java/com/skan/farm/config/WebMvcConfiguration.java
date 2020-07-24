package com.skan.farm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <pre>
 * Description :  우선순위가 WebMvcConfigurer 높으나
 *                  corsFilter 에서 걸림으로 상세 한건 corsFilter 에 설정 한다.
 *
 *                  <link> https://spring.getdocs.org/en-US/spring-framework-docs/docs/spring-web/mvc/webmvc-fn.html </link>
 *
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2020 by CJENM|Mezzomedia. All right reserved.
 * @since 2020-07-21
 */
@Configuration
@EnableWebMvc
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") //모든 요청에 대해서
                //.allowedOrigins("http://localhost:3000","http://localhost:8080","http://localhost:80") //허용할 오리진들
                .allowedOrigins("*")
        ;

    }

    @Bean
    CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();

        // Possibly...
        // config.applyPermitDefaultValues()

        config.setAllowCredentials(true);
        config.addAllowedOrigin("https://localhost:8080");
        config.addAllowedOrigin("http://localhost:8080");
        //config.addAllowedOrigin("*");

        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }

//    Webfliux cors
//    @Bean
//    CorsWebFilter corsWebFilter(){
//        CorsConfiguration config = new CorsConfiguration();
//
//        // Possibly...
//        // config.applyPermitDefaultValues()
//
//        config.setAllowCredentials(true);
//        config.addAllowedOrigin("https://localhost:8080");
//        config.addAllowedOrigin("https://localhost:8000");
//        config.addAllowedOrigin("https://localhost:3000");
//        config.addAllowedOrigin("https://localhost:80");
//
//        config.addAllowedHeader("*");
//        config.addAllowedMethod("*");
//
//        org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource source = new org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", config);
//
//        return new CorsWebFilter(source);
//    }
}

