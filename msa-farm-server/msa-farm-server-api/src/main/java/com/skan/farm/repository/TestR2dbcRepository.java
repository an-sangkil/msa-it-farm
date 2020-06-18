package com.skan.farm.repository;

import com.skan.farm.model.Users;
import io.r2dbc.spi.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.r2dbc.connectionfactory.R2dbcTransactionManager;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.reactive.TransactionalOperator;
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

    private final ConnectionFactory connectionFactory;
    private final DatabaseClient databaseClient;

    public TestR2dbcRepository(ConnectionFactory connectionFactory, DatabaseClient databaseClient) {
        this.connectionFactory = connectionFactory;
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
                .bind("user_id", userId)
                .as(Users.class)
                .fetch()
                .one()
                .switchIfEmpty(Mono.defer(Mono::empty))
                .doOnError(Throwable::printStackTrace)


                ;
    }

    public Mono<Integer> insert(String name, String id) {
        return databaseClient
                .execute("insert into users (user_name, user_id)  values (:user_name, :user_id )")
                .bind("user_name", name)
                .bind("user_id", id)
                .fetch()
                .rowsUpdated()
                ;
    }

    public void insertCriteria(String name) {
        databaseClient
                .insert()
                .into(Users.class).using(new Users("4", name))
                .then();
        ;
    }


    public Mono<Void> update(String name, String id) {
        return databaseClient
                .execute("update users set user_name = :user_name where user_id = :user_id")
                .bind("user_name", name)
                .bind("user_id", id)
                .then()
                ;
    }

    public Mono<Void> updateCriteria(String id, String name) {
        return databaseClient
                .update()
                .table(Users.class).using(new Users(id, name))
                .then()
                ;
    }

    public Mono<Void> delete(String id) {

        return Mono.empty();
    }

    /**
     * Criteria 방식의 삭제
     * @param id
     * @return
     */
    public Mono<Void> deleteCriteria(String id) {

        return databaseClient.delete()
                .from(Users.class)
                .matching(Criteria.where("user_id").is(id))
                .then();
    }

    /**
     * 프로그램 트랜잭션
     * @param id
     * @param name
     * @return
     */
    public Mono<Void> insertProgramTransaction(@NonNull String id, @Nullable String name) {

        R2dbcTransactionManager tm = new R2dbcTransactionManager(connectionFactory);
        TransactionalOperator operator = TransactionalOperator.create(tm);

        return databaseClient.execute("select * from users where user_id =:user_id")
                .bind("user_id", id)
                .fetch()
                .one()
                .then(databaseClient.execute("update users set user_name = :user_name where user_id = :user_id")
                        .bind("user_id", id)
                        .bind("user_name", name)
                        .fetch().rowsUpdated()
                )
                .then(
                        databaseClient.execute("INSERT INTO users (user_id, user_name) VALUES(:id, :name)")
                                .bind("id", "joe")
                                .bind("name", "Joe")
                                .fetch().rowsUpdated()
                )
                .then()
                .as(operator::transactional)

                ;
    }

    /**
     * 선언적 트랜잭션  이용
     * {@link com.skan.farm.config.Config#transactionManager(ConnectionFactory)} 에 ReactiveTransactionManager 생성 후 사용
     *
     * @param id
     * @param name
     * @return
     */
    @Transactional
    public Mono<Integer> declarativeInsertTransaction(@NonNull String id, @Nullable String name) {

        return
                databaseClient.execute("update users set user_name = :user_name where user_id = :user_id")
                        .bind("user_id", id)
                        .bind("user_name", name)
                        .fetch().rowsUpdated()

                        .then(
                                databaseClient.execute("INSERT INTO users (user_id, user_name) VALUES(:id, :name)")
                                        .bind("id", id)
                                        .bind("name", name)
                                        .fetch().rowsUpdated()
                        )
                ;
    }
}
