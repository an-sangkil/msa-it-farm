package com.skan.farm.router;

import com.skan.farm.model.LocalBeefManagement;
import com.skan.farm.model.LocalBeefManagementPK;
import com.skan.farm.repository.jpa.LocalBeefManagementJpaRepository;
import com.skan.farm.service.CattleManagementService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import static org.springframework.web.servlet.function.RequestPredicates.*;

import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

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
@Component
@AllArgsConstructor
public class RouterCattleManagement {

    private final LocalBeefManagementJpaRepository localBeefManagementJpaRepository;
    private final CattleManagementService cattleManagementService;

    @Bean
    RouterFunction<ServerResponse> RouterFunction() {
        return RouterFunctions.route(GET("/cattle/cattle_management_list"), request -> {

            var entityId = request.param("entity_management_id").orElseThrow();
            var identityId = request.param("identity_management_id").orElseThrow();

            LocalBeefManagement localBeefManagement = LocalBeefManagement
                    .builder()
                    .localBeefManagementPK(new LocalBeefManagementPK(entityId, identityId)).build();




            return ServerResponse.ok().body(cattleManagementService.findAllPaging(localBeefManagement, null, null));
        }).andRoute(GET("/cattle/detail"), request -> {

            var entityId = request.param("entity_management_id").orElseThrow();
            var identityId = request.param("identity_management_id").orElseThrow();

            return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(
                    this.localBeefManagementJpaRepository.findById(new LocalBeefManagementPK(entityId, identityId))
            );
        }).filter((request, next) -> {
            return next.handle(request);
        });
    }
}
