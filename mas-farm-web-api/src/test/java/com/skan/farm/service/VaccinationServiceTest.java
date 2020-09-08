package com.skan.farm.service;

import com.skan.farm.domain.entity.Vaccination;
import com.skan.farm.repository.TestNonConfiguration;
import lombok.AllArgsConstructor;
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
 * @since 2020-09-08
 */
@Slf4j
@TestNonConfiguration
@RequiredArgsConstructor
class VaccinationServiceTest {

    final VaccinationService vaccinationService;

    final String entityNumber = "1111";
    final String identityNumber = "2222";

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void save() {

        Vaccination vaccination = Vaccination.builder()
                .vaccinationPK(new Vaccination.VaccinationPK(LocalDate.now(),
                        identityNumber,entityNumber, (short) 2))
                .vaccinationType("구충제")
                .location("가동1번")
                .build();

        vaccinationService.save(vaccination);
    }
}