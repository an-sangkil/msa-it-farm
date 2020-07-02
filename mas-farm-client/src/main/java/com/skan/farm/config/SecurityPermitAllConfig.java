//package com.skan.farm.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//
///**
// * <pre>
// * Description :
// *
// *
// * </pre>
// *
// * @author skan
// * @version Copyright (C) 2020 by CJENM|Mezzomedia. All right reserved.
// * @since 2020-07-02
// */
//@Configuration
//public class SecurityPermitAllConfig extends org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().anyRequest().permitAll()
//                .and().csrf().disable();
//    }
//}