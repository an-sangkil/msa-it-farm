package com.skan.farm.repository.jpa;

import com.skan.farm.domain.entity.PersonalCode;
import com.skan.farm.repository.TestNonConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

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
class PersonalCodeJpaRepositoryTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    PersonalCodeJpaRepository personalCodeJpaRepository;

    public PersonalCodeJpaRepositoryTest(PersonalCodeJpaRepository personalCodeJpaRepository) {
        this.personalCodeJpaRepository = personalCodeJpaRepository;
    }


    @Test
    void insertAndSelect() {
        this.personalCodeInsert();
        this.select();
    }

    @Test
    void personalCodeInsert() {
        PersonalCode personalCode = new PersonalCode();
        personalCode.setCode("00001");
        personalCode.setCodeName("개인");
        personalCode.setUserId("skan");
        personalCode.setCodeSeq(1);

        personalCodeJpaRepository.save(personalCode);

        PersonalCode personalCode1 = new PersonalCode();
        personalCode1.setCode("00002");
        personalCode1.setCodeName("개인-child");
        personalCode1.setParentCode(personalCode);
        personalCode1.setUserId("skan");
        personalCode1.setCodeSeq(1);

        personalCodeJpaRepository.save(personalCode1);

    }

    @Test
    @DisplayName("필수 데이터를 넣지 않아 발생되는 에러 케이스")
    void personalCodeInsertError() {
        Exception exception = Assertions.assertThrows(Exception.class, this::insert);
        log.error("exception :",  exception );
    }

    void insert() {
        PersonalCode personalCode = new PersonalCode();
        personalCode.setCode("00001");
        personalCode.setCodeName("개인");
        personalCode.setCodeSeq(1);

        personalCodeJpaRepository.save(personalCode);

        PersonalCode personalCode1 = new PersonalCode();
        personalCode1.setCode("00002");
        personalCode1.setCodeName("개인-child");
        personalCode1.setParentCode(personalCode);
        personalCode1.setCodeSeq(1);

        personalCodeJpaRepository.save(personalCode1);
    }


    @Test
    void select() {
        PersonalCode personalCode = personalCodeJpaRepository.findById("00001").orElseThrow();
        log.debug("personalCode  : {}", personalCode);

        PersonalCode personalCode1 = personalCodeJpaRepository.findById("00002").orElseThrow();
        log.debug("commonCode2  : {}", personalCode1);
    }
}