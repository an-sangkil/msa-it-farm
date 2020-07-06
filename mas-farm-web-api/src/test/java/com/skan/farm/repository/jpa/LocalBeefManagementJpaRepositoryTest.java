package com.skan.farm.repository.jpa;

import com.skan.farm.repository.TestCodeGeneration;
import com.skan.farm.repository.TestNonConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
 * @since 2020-07-06
 */
@TestNonConfiguration
@Slf4j
class LocalBeefManagementJpaRepositoryTest implements TestCodeGeneration {
    LocalBeefManagementJpaRepository localBeefManagementJpaRepository;

    public LocalBeefManagementJpaRepositoryTest(LocalBeefManagementJpaRepository localBeefManagementJpaRepository) {
        this.localBeefManagementJpaRepository = localBeefManagementJpaRepository;
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }


    @Override
    public void saveSelect() {

    }

    @Test
    @DisplayName("기본 저장 ")
    @Override
    public void save() {

    }

    @Override
    public void select() {

    }

    @Override
    public void delete() {

    }
}