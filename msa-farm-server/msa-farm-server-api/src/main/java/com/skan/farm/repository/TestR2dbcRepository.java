package com.skan.farm.repository;

import com.skan.farm.model.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * <pre>
 * Description :
 *
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2020 by CJENM|Mezzomedia. All right reserved.
 * @since 2020-06-16
 */
@Repository
@Slf4j
public class TestR2dbcRepository {

    private final DatabaseClient databaseClient;

    public TestR2dbcRepository(DatabaseClient databaseClient) {
        this.databaseClient = databaseClient;
    }

    public Flux<Users> getList() {
        return databaseClient.select()
                .from("users")
                .as(Users.class)
                .fetch()
                .all()
                .switchIfEmpty(Flux.defer(Flux::empty))
                .doOnError(Throwable::printStackTrace)


        ;
    }
    public Mono<Users> getOne(String userId) {
        return databaseClient.select()
                .from("users")
                .matching(Criteria.where("user_id").in(userId))
                .as(Users.class)
                .fetch()
                .one()
                .switchIfEmpty(Mono.defer(Mono::empty))
                .doOnError(Throwable::printStackTrace)
                ;

    }

    public Mono<Users> getOneV2(String userId) {
        return databaseClient.
                execute("select * from users where user_id = :user_id")
                .bind("user_id",userId)
                .as(Users.class)
                .fetch()
                .one()
                .switchIfEmpty(Mono.defer(Mono::empty))
                .doOnError(Throwable::printStackTrace)


        ;
    }

}
