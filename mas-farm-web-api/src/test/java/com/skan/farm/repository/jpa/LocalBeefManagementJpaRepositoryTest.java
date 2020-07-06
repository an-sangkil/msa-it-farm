package com.skan.farm.repository.jpa;

import com.skan.farm.code.GenderCode;
import com.skan.farm.model.CattleBuyInformation;
import com.skan.farm.model.LocalBeefManagement;
import com.skan.farm.repository.TestCodeGeneration;
import com.skan.farm.repository.TestNonConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

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
@RequiredArgsConstructor
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LocalBeefManagementJpaRepositoryTest implements TestCodeGeneration {
    final LocalBeefManagementJpaRepository localBeefManagementJpaRepository;
    final CattleBuyInformationJpaRepository cattleBuyInformationJpaRepository;

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
    @Order(1)
    public void save() {

        LocalBeefManagement localBeefManagement = LocalBeefManagement.builder()
                .localBeefManagementPK(new LocalBeefManagement.LocalBeefManagementPK("1234", "5678"))
                .birthDay(LocalDate.now())
                .gender(GenderCode.FEMALE)
                .build();

        this.localBeefManagementJpaRepository.save(localBeefManagement);
    }

    @Test
    @DisplayName("판매 기본 저장")
    @Order(2)
    void cattleBuySave() {

        CattleBuyInformation cattleBuyInformation = CattleBuyInformation.builder()
                .cattleBuyInformationPK(new CattleBuyInformation.CattleBuyInformationPK("1234", "5678"))
                .buyDate(LocalDate.now())
                .buyNote("노트 처음")
                .build();

        this.cattleBuyInformationJpaRepository.save(cattleBuyInformation);
    }

    @Test
    @Order(3)
    void findAndSave() {
        CattleBuyInformation cattleBuyInformation = cattleBuyInformationJpaRepository.findById(new CattleBuyInformation.CattleBuyInformationPK("1234", "5678")).orElseThrow();
        LocalBeefManagement localBeefManagement = localBeefManagementJpaRepository.findById(new LocalBeefManagement.LocalBeefManagementPK("1234", "5678")).orElseThrow();

        System.out.println("---------------------------------- 절취선 -------------------------------------------");
        cattleBuyInformation.setBuyNote("노트트트 수정");
        //localBeefManagement.setCattleBuyInformation(cattleBuyInformation);
                

    }


    @Override
    public void select() {

    }

    @Override
    public void delete() {

    }
}