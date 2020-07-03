package com.skan.farm.config;//package com.skan.farm.config;
//
//import org.jooq.impl.DataSourceConnectionProvider;
//import org.jooq.impl.DefaultConfiguration;
//import org.jooq.impl.DefaultDSLContext;
//import org.jooq.impl.DefaultExecuteListenerProvider;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.jooq.JooqExceptionTranslator;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
//
//import javax.sql.DataSource;
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
// * @since 2020-06-16
// */
//@Configuration
//public class JooqConfiguration {
//
//    @Autowired
//    private DataSource dataSource;
//
//    @Bean
//    public DataSourceConnectionProvider connectionProvider() {
//        return new DataSourceConnectionProvider
//                (new TransactionAwareDataSourceProxy(dataSource));
//    }
//
//    @Bean
//    public DefaultDSLContext dsl() {
//        return new DefaultDSLContext(configuration());
//    }
//
//    public DefaultConfiguration configuration() {
//        DefaultConfiguration jooqConfiguration = new DefaultConfiguration();
//        jooqConfiguration.set(connectionProvider());
//        jooqConfiguration
//                .set(new DefaultExecuteListenerProvider(new JooqExceptionTranslator()));
//
//        return jooqConfiguration;
//    }
//
//}
