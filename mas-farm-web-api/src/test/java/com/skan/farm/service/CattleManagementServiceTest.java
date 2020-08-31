package com.skan.farm.service;

import com.skan.farm.domain.entity.LocalBeefManagement;
import com.skan.farm.repository.TestNonConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Slf4j
@TestNonConfiguration
class CattleManagementServiceTest {

    @Autowired
    CattleManagementService cattleManagementService;

    @Test
    void findAllPaging() {
    }

    @Test
    void findAll() {
        var page = 11;
        var size = 20;

        Pageable pageable = PageRequest.of(page, size);
        Page<LocalBeefManagement> managements =  cattleManagementService.findAllPaging(pageable);
        log.debug("managements.getSize()= {}", managements.getSize());
        log.debug("managements.getPageable()= {}", managements.getPageable());
        log.debug("managements.getNumber()= {}", managements.getNumber());
        log.debug("managements.getNumberOfElements()= {}", managements.getNumberOfElements());
        log.debug("managements.getTotalElements()= {}", managements.getTotalElements());
        log.debug("managements.getTotalPages() =  {}", managements.getTotalPages());
    }
}