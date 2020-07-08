package com.skan.farm.router;

import com.skan.farm.model.Users;
import org.jooq.User;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

/**
 * <pre>
 * Description :
 *
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2020 by CJENM|Mezzomedia. All right reserved.
 * @since 2020-07-08
 */
@Component
public class Router {


    @Bean
    RouterFunction<ServerResponse> routerFunction() {

        return
                RouterFunctions.route(RequestPredicates.GET("/router/test"), request -> ServerResponse.ok().body("mvc router test")).
                        andRoute(RequestPredicates.GET("/router/test2"), request -> ServerResponse.ok().body(this.getTestRouterUser()));
    }

    List<Users> getTestRouterUser() {
        List<Users> users = new ArrayList<>();
        IntStream.range(0, 10).forEach(value -> {

            users.add(new Users(UUID.randomUUID().toString(), "" + value));
        });
        return users;
    }
}


