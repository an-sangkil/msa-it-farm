package com.skan.farm.repository.jooq;

import com.skan.farm.repository.TestNonConfiguration;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
 * @since 2020-07-14
 */
@TestNonConfiguration
@RequiredArgsConstructor
class CalvesManagementJooqRepositoryTest {

    final CalvesManagementJooqRepository calvesManagementJooqRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findByLastCount() {
        calvesManagementJooqRepository.findByLastCount("1111","2222");
    }
}