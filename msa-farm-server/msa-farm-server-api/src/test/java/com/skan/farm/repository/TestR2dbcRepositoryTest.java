package com.skan.farm.repository;

import com.skan.farm.model.Users;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

import static org.junit.jupiter.api.Assertions.*;

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
@ActiveProfiles("local")
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@Slf4j
class TestR2dbcRepositoryTest {

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
        String userId = "dmlim";
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
}