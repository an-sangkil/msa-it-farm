package com.skan.farm.service;

import com.skan.farm.model.CalvesManagement;
import com.skan.farm.repository.TestNonConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

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
@Slf4j
@TestNonConfiguration
@RequiredArgsConstructor
class ChildBirthManagementServiceTest {

    final ChildBirthManagementService childBirthManagementService;

    @Test
    void save() {
        String entityNumber = "1111";
        String identityNumber = "2222";

        CalvesManagement calvesManagement = CalvesManagement.builder()
                .nothingSpecial("하하2")
                .calvingDate(LocalDateTime.now())
                .years("2020")
                .spermNo("111111")
                .fertilizationDate(LocalDateTime.now())
                .build();
        childBirthManagementService.save(calvesManagement, entityNumber, identityNumber);
    }

    @Test
    void expectedDateConfinementCalculate() {
        LocalDateTime dateTime;
        dateTime = childBirthManagementService.expectedDateConfinementCalculate(LocalDateTime.of(2020, 4, 1, 0, 0, 0));
        log.debug(" 2020-04-01 dateTime = {} ", dateTime);

        dateTime = childBirthManagementService.expectedDateConfinementCalculate(LocalDateTime.of(2020, 4, 21, 0, 0, 0));
        log.debug(" 2020-04-21 dateTime = {} ", dateTime);

        dateTime = childBirthManagementService.expectedDateConfinementCalculate(LocalDateTime.of(2020, 4, 22, 0, 0, 0));
        log.debug(" 2020-04-22 dateTime = {} ", dateTime);


        dateTime = childBirthManagementService.expectedDateConfinementCalculate(LocalDateTime.of(2020, 3, 1, 0, 0, 0));
        log.debug(" 2020-03-01 dateTime = {} ", dateTime);

        dateTime = childBirthManagementService.expectedDateConfinementCalculate(LocalDateTime.of(2020, 3, 21, 0, 0, 0));
        log.debug(" 2020-03-21 dateTime = {} ", dateTime);


        dateTime = childBirthManagementService.expectedDateConfinementCalculate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        log.debug(" 2020-01-01 dateTime = {} ", dateTime);

        dateTime = childBirthManagementService.expectedDateConfinementCalculate(LocalDateTime.of(2020, 1, 21, 0, 0, 0));
        log.debug(" 2020-01-21 dateTime = {} ", dateTime);

        dateTime = childBirthManagementService.expectedDateConfinementCalculate(LocalDateTime.of(2020, 2, 1, 0, 0, 0));
        log.debug(" 2020-02-01 dateTime = {} ", dateTime);

        dateTime = childBirthManagementService.expectedDateConfinementCalculate(LocalDateTime.of(2020, 2, 21, 0, 0, 0));
        log.debug(" 2020-02-21 dateTime = {} ", dateTime);
    }
}