package com.skan.farm.repository.jpa;

import com.skan.farm.model.CommonCode;
import com.skan.farm.repository.TestNonConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
@Slf4j
@TestNonConfiguration
class CommonCodeJpaRepositoryTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    CommonCodeJpaRepository commonCodeJpaRepository;

    public CommonCodeJpaRepositoryTest(CommonCodeJpaRepository commonCodeJpaRepository) {
        this.commonCodeJpaRepository = commonCodeJpaRepository;
    }

    @Test
    void insertAndSelect() {
        this.commonCodeInsert();
        this.select();
    }

    @Test
    void commonCodeInsert() {
        CommonCode commonCode = new CommonCode();
        commonCode.setCode("00001");
        commonCode.setCodeName("열일");
        commonCode.setCodeSeq(1);

        commonCodeJpaRepository.save(commonCode);

        CommonCode commonCode2 = new CommonCode();
        commonCode2.setCode("00002");
        commonCode2.setCodeName("열일-child");
        commonCode2.setParentCode(commonCode);
        commonCode2.setCodeSeq(1);

        commonCodeJpaRepository.save(commonCode2);

    }


    @Test
    void select() {
        CommonCode commonCode = commonCodeJpaRepository.findById("00001").orElseThrow();
        log.debug("commonCode  : {}", commonCode);

        CommonCode commonCode2 = commonCodeJpaRepository.findById("00002").orElseThrow();
        log.debug("commonCode2  : {}", commonCode2);
    }
}