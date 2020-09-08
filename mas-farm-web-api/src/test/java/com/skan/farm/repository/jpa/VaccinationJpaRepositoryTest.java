package com.skan.farm.repository.jpa;

import com.skan.farm.repository.TestNonConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

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
 * @since 2020-09-07
 */
@Slf4j
@TestNonConfiguration
@RequiredArgsConstructor
class VaccinationJpaRepositoryTest {
    final VaccinationJpaRepository vaccinationJpaRepository;

    String entityNumber = "1111";
    String identityNumber = "2222";

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void save() {
        long count = vaccinationJpaRepository.countByVaccinationPKEntityIdentificationNumberAndVaccinationPKDay("111", LocalDate.of(2020, 9, 8));
        log.debug("count = {}", count);
    }
}