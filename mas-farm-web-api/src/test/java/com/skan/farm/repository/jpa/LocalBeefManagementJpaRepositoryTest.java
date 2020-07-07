package com.skan.farm.repository.jpa;

import com.skan.farm.code.GenderCode;
import com.skan.farm.model.CattleBuyInformation;
import com.skan.farm.model.CattleBuyInformationPK;
import com.skan.farm.model.LocalBeefManagement;
import com.skan.farm.model.LocalBeefManagementPK;
import com.skan.farm.repository.TestCodeGeneration;
import com.skan.farm.repository.TestNonConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

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
        IntStream.range(0, 100).forEach(value -> {

        });

//        LocalBeefManagement localBeefManagement = LocalBeefManagement.builder()
//                .localBeefManagementPK(new LocalBeefManagementPK("1234", "5678"))
//                .birthDay(LocalDate.now())
//                .gender(GenderCode.FEMALE)
//                .build();
        LocalBeefManagement localBeefManagement = new LocalBeefManagement();
        localBeefManagement.setEntityManagementNumber("1234");
        localBeefManagement.setBirthDay(LocalDate.now());
        localBeefManagement.setGender(GenderCode.FEMALE);


        this.localBeefManagementJpaRepository.save(localBeefManagement);
    }

    @Test
    @DisplayName("판매 기본 저장")
    @Order(2)
    void cattleBuySave() {

//        CattleBuyInformation cattleBuyInformation = CattleBuyInformation.builder()
//                .cattleBuyInformationPK(new CattleBuyInformationPK("1234", "5678"))
//                .buyDate(LocalDate.now())
//                .buyNote("노트 처음")
//                .build();

        CattleBuyInformation cattleBuyInformation = new CattleBuyInformation();
        cattleBuyInformation.setCattleEntityManagementNumber("1234");
        cattleBuyInformation.setBuyDate(LocalDate.now());
        cattleBuyInformation.setBuyNote("노트 처음");

        this.cattleBuyInformationJpaRepository.save(cattleBuyInformation);
    }

    @Test
    @Order(3)
    @DisplayName("저장된 판매내역 불러와 수정 하면서 업데이트 하기 ")
    void findAndSave() {

        cattleBuyInformationJpaRepository.findAll();
//        localBeefManagementJpaRepository.findAll();
//        LocalBeefManagement localBeefManagement = localBeefManagementJpaRepository.findById(new LocalBeefManagement.LocalBeefManagementPK("1234", "5678")).orElseThrow();
//        CattleBuyInformation cattleBuyInformation = cattleBuyInformationJpaRepository.findById(new CattleBuyInformation.CattleBuyInformationPK("1234", "5678")).orElseThrow();
//
//        System.out.println("---------------------------------- 절취선 -------------------------------------------");
//        cattleBuyInformation.setBuyNote("노트트트 수정");
//        System.out.println(localBeefManagement.getBirthDay());
        //System.out.println(localBeefManagement.getCattleBuyInformation());
        //localBeefManagement.setCattleBuyInformation(cattleBuyInformation);

        System.out.println("end");
    }

    @Test
    @Order(4)
    @DisplayName("부모객체에 자식객체 추가하여 한번에 저장하기 : 영속성 전이 관계를 설정하여야만 동작 한다.")
    void localbeefAndCattleSave() {
//        LocalBeefManagement localBeefManagement = LocalBeefManagement.builder()
//                .localBeefManagementPK(new LocalBeefManagement.LocalBeefManagementPK("4444", "5555"))
//                .birthDay(LocalDate.now())
//                .gender(GenderCode.FEMALE)
//                .build();
//
//        localBeefManagement.setCattleBuyInformation(CattleBuyInformation.builder()
//                .cattleBuyInformationPK(new CattleBuyInformation.CattleBuyInformationPK("4444", "5555"))
//                .buyDate(LocalDate.now())
//                .buyNote("노트 처음")
//                .build());
//
//        this.localBeefManagementJpaRepository.save(localBeefManagement);

    }

    @Test
    @Order(5)
    @DisplayName("자식객체에 부모객체를 삽입하여 저장하기 : 영속성 전이 관계가 있어야만 작동된다.")
    void localbeefAndCattleSaveReverse() {
        /*LocalBeefManagement localBeefManagement = LocalBeefManagement.builder()
                .localBeefManagementPK(new LocalBeefManagementPK("5555", "6666"))
                .birthDay(LocalDate.now())
                .gender(GenderCode.FEMALE)
                .build();
        CattleBuyInformation cattleBuyInformation = CattleBuyInformation.builder()
                .cattleBuyInformationPK(new CattleBuyInformationPK("5555", "6666"))
                .buyDate(LocalDate.now())
                .buyNote("노트 처음")
                .build();*/

        LocalBeefManagement localBeefManagement = new LocalBeefManagement();
        localBeefManagement.setEntityManagementNumber("1234");
        localBeefManagement.setBirthDay(LocalDate.now());
        localBeefManagement.setGender(GenderCode.FEMALE);

        CattleBuyInformation cattleBuyInformation = new CattleBuyInformation();
        cattleBuyInformation.setCattleEntityManagementNumber("1234");
        cattleBuyInformation.setBuyDate(LocalDate.now());
        cattleBuyInformation.setBuyNote("노트 처음");

        cattleBuyInformation.setLocalBeefManagement(localBeefManagement);

        this.cattleBuyInformationJpaRepository.save(cattleBuyInformation);
    }

    @Test
    @DisplayName("여러개 저장 후 레이지 로딩 동작 확인 - 부모기준 양방향 맵핑시")
    @Order(6)
    public void saveManyParent_양_단_방향() {
        List<LocalBeefManagement> localBeefManagements = new ArrayList<>();
        IntStream.range(0, 100).forEach(value -> {
            LocalBeefManagement localBeefManagement = new LocalBeefManagement();
            localBeefManagement.setEntityManagementNumber("1234" + value);
            localBeefManagement.setBirthDay(LocalDate.now());
            localBeefManagement.setGender(GenderCode.FEMALE);

            CattleBuyInformation cattleBuyInformation = new CattleBuyInformation();
            cattleBuyInformation.setCattleEntityManagementNumber("1234" + value);
            cattleBuyInformation.setBuyDate(LocalDate.now());
            cattleBuyInformation.setBuyNote("노트 처음" + value);

            cattleBuyInformation.setLocalBeefManagement(localBeefManagement);
            localBeefManagement.setCattleBuyInformation(cattleBuyInformation);

            localBeefManagements.add(localBeefManagement);
        });

        this.localBeefManagementJpaRepository.saveAll(localBeefManagements);

        System.out.println("-------------------------------- 조회 시작 ----------------------------");
        List<LocalBeefManagement> beefManagements = this.localBeefManagementJpaRepository.findAll();
        beefManagements.forEach(localBeefManagement -> {
            log.debug("beefManagement getCattleBuyInformation info : {} ", localBeefManagement.getCattleBuyInformation());
        });
        log.debug("beef size = {}", beefManagements.size());

    }

    @Test
    @DisplayName("여러개 저장 후 레이지 로딩 동작 확인 - 부모기준 양방향 맵핑시")
    @Order(7)
    public void selectParent_양_단_방향() {
        System.out.println("-------------------------------- 조회 시작 ----------------------------");
        List<LocalBeefManagement> beefManagements = this.localBeefManagementJpaRepository.findAll();

        beefManagements.forEach(localBeefManagement -> {
            log.debug("beefManagement getCattleBuyInformation info : {} ", localBeefManagement.getCattleBuyInformation());
        });
        log.debug("beef size = {}", beefManagements.size());

    }


    @Test
    @DisplayName("여러개 저장 후 레이지 로딩 동작 확인 - 자식 기준 단방향 맵핑시")
    @Order(7)
    public void saveManyChild_단방향() {
        List<CattleBuyInformation> cattleBuyInformations = new ArrayList<>();
        IntStream.range(0, 100).forEach(value -> {
            LocalBeefManagement localBeefManagement = new LocalBeefManagement();
            localBeefManagement.setEntityManagementNumber("1234" + value);
            localBeefManagement.setBirthDay(LocalDate.now());
            localBeefManagement.setGender(GenderCode.FEMALE);

            CattleBuyInformation cattleBuyInformation = new CattleBuyInformation();
            cattleBuyInformation.setCattleEntityManagementNumber("1234" + value);
            cattleBuyInformation.setBuyDate(LocalDate.now());
            cattleBuyInformation.setBuyNote("노트 처음" + value);

            cattleBuyInformation.setLocalBeefManagement(localBeefManagement);

            cattleBuyInformations.add(cattleBuyInformation);
        });

        this.cattleBuyInformationJpaRepository.saveAll(cattleBuyInformations);

        System.out.println("-------------------------------- 조회 시작 ----------------------------");
        List<CattleBuyInformation> beefManagements = this.cattleBuyInformationJpaRepository.findAll();
        beefManagements.forEach(cattleBuyInformation -> {
            log.debug("cattleBuyInformation information = {}", cattleBuyInformation.getLocalBeefManagement());
        });


    }

    /**
     * lazy 로딩 안됨
     */
    @Test
    @DisplayName("여러개 저장 후 레이지 로딩 동작 확인 - 자식 기준 단방향 맵핑시")
    @Order(7)
    public void selectChild_단방향() {
        System.out.println("-------------------------------- 조회 시작 ----------------------------");
        List<CattleBuyInformation> beefManagements = this.cattleBuyInformationJpaRepository.findAll();
        beefManagements.forEach(cattleBuyInformation -> {
            log.debug("cattleBuyInformation information = {}", cattleBuyInformation.getLocalBeefManagement());
        });


    }

    @Override
    public void select() {

    }

    @Override
    public void delete() {

    }
}