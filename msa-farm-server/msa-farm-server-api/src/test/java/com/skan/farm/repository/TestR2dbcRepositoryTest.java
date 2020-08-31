package com.skan.farm.repository;

import com.skan.farm.domain.entity.Users;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

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
@SpringBootTest
@ActiveProfiles("test")
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@Slf4j
class TestR2dbcRepositoryTest {

    //TimeUtils.secondsToUnit(60,TimeUnit.SECONDS);
    TestR2dbcRepository testR2dbcRepository;

    public TestR2dbcRepositoryTest(TestR2dbcRepository testR2dbcRepository) {
        this.testR2dbcRepository = testR2dbcRepository;
    }

    @Test
    void getOne() {
        String userId = "1";
        Mono<Users> usersMono = testR2dbcRepository.getOne(userId);
        usersMono.subscribe(users -> {
            log.debug(" getOne user data = {} ", users);
        });

        StepVerifier.create(usersMono)
                //.expectNext(new Users("",""))
                .expectNextCount(1)
                .verifyComplete()

        ;
    }

    @Test
    void getOneV2() {
        var userId = "1";
        Mono<Users> usersMono = testR2dbcRepository.getOneV2(userId);
        usersMono.subscribe(users -> {
            log.debug(" getOneV2 user data = {} ", users);
        });

        StepVerifier.create(usersMono)
                //.expectNext(new Users("",""))
                .expectNextCount(1)
                .verifyComplete()

        ;
    }

    @Test
    void getList() throws InterruptedException {
        Flux<Users> usersFlux = testR2dbcRepository.getList();
        usersFlux.subscribe(users -> {
            log.debug(" getList user data = {} ", users);
        });

        StepVerifier.create(usersFlux)
                .expectNextCount(58)
                .expectComplete()
                //.expectTimeout(Duration.of(60, ChronoUnit.SECONDS))
                .verify()
        ;


        ;
    }

    @Test
    void insert() throws InterruptedException {
        var id = "3";
        var name = "userName";
        StepVerifier.create(testR2dbcRepository.insert(name, id))
                .consumeNextWith(integer -> {
                    log.debug("data = {}", integer);
                })
                .expectComplete()
                .verify();
        ;

    }

    @Test
    @Rollback(false)
    void update() {
        var id = "1";
        var name = "userName update";
        StepVerifier.create(testR2dbcRepository.update(name, id))
                .verifyComplete();
    }


    @Test
    @Rollback(false)
    void updateCriteria() {
        var id = "1";
        var name = "userName update criteria";
        StepVerifier.create(testR2dbcRepository.updateCriteria(id, name))
                .verifyComplete();
    }

    @Test
    void deleteCriteria() {
        var id = "1";
        var name = "name";
        StepVerifier.create(testR2dbcRepository.deleteCriteria(id))
                .verifyComplete();
    }

    @Test
    void programTransactionTest() {
        var id = "1";
        var name = "name";
        StepVerifier.create(testR2dbcRepository.insertProgramTransaction(id, name))
                .verifyComplete();
    }

    @Test
    void declarativeInsertTransactionTest() {
        var id = "1";
        var name = "name";
        StepVerifier.create(testR2dbcRepository.declarativeInsertTransaction(id, name))
                .verifyComplete();
    }
}