package com.skan.farm.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

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
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestConstructor;

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
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@ActiveProfiles("local")
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
}