package com.skan.farm.repository.jooq;

import com.skan.farm.paging.PageableRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
 * @version Copyright (C) 2020 by SKAN . All right reserved.
 * @since 2020-08-28
 */
@Slf4j
@SpringBootTest
@ActiveProfiles("test")
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class SalesStatusJooqRepositoryTest {


    final SalesStatusJooqRepository salesStatusJooqRepository;

    @Test
    void findByAll() {
        PageableRequest pageable = new PageableRequest(0, 100);
        salesStatusJooqRepository.findByAll(null, pageable);
    }
}