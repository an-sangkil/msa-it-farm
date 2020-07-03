package com.skan.farm.repository.jooq;

import com.skan.farm.model.Users;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestConstructor;

import java.util.UUID;

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
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@ActiveProfiles("test")
@Slf4j
class TestJooqRepositoryTest {

    TestJooqRepository testJooqRepository;

    public TestJooqRepositoryTest(TestJooqRepository testJooqRepository) {
        this.testJooqRepository = testJooqRepository;
    }

    @Test
    void getList() {
        testJooqRepository.getList()
                .forEach(stringObjectMap -> {
                    stringObjectMap.forEach((s, o) -> {
                        log.debug("key : {} value {}", s, o);
                    });
                });
    }

    @Test
    void getListObject() {
        testJooqRepository.getListObject()
                .forEach(user -> {
                    log.debug("value {}", user);
                });
    }

    @Test
    @DisplayName("DSL 타입 ")
    void getListDSLType() {
        testJooqRepository.getListDSLType()
                .forEach(user -> {
                    log.debug("user info = {}", user);
                });
    }

    @Test
    void insert() {
        Users users = new Users();
        users.setUuid(UUID.randomUUID().toString());
        users.setUserId("skan");
        users.setUserName("skan");
        users.setPassword("password");

        testJooqRepository.insert(users);
    }

    @Test
    void delete() {
        String key = "skan";
        testJooqRepository.delete(key);
    }
}