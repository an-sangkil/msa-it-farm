package com.skan.farm.repository.jpa;

import com.skan.farm.code.GenderCode;
import com.skan.farm.model.CattleBuyInformation;
import com.skan.farm.model.LocalBeefManagement;
import com.skan.farm.model.LocalBeefManagementPK;
import com.skan.farm.repository.TestCodeGeneration;
import com.skan.farm.repository.TestNonConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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


    String entityNumber = "1111";
    String identityNumber = "2222";

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
    @DisplayName("기록관리 기본 저장 ")
    @Override
    @Order(1)
    public void save() {


        LocalBeefManagement localBeefManagement = LocalBeefManagement.builder()
                .localBeefManagementPK(new LocalBeefManagementPK(entityNumber, identityNumber))
                .birthDay(LocalDate.now())
                .gender(GenderCode.FEMALE)
                .build();


        this.localBeefManagementJpaRepository.save(localBeefManagement);
    }

    @Test
    @DisplayName("판매 기본 저장")
    @Order(2)
    void cattleBuySave() {

        // parent key 가 없으면 제약 조건 위배로 오류.
        CattleBuyInformation cattleBuyInformation = new CattleBuyInformation();
        cattleBuyInformation.setCattleBuyInformationPK(new LocalBeefManagementPK("1111", "2222"));
        cattleBuyInformation.setBuyDate(LocalDate.now());
        cattleBuyInformation.setBuyNote("노트 처음");

        cattleBuyInformation = this.cattleBuyInformationJpaRepository.save(cattleBuyInformation);
        log.debug("cattleBuyInformation 저장 정보 : {}", cattleBuyInformation);
    }

    @Test
    @Order(3)
    @DisplayName("저장된 판매내역 불러와 수정 하면서 업데이트 하기 - 준영속성 전파로 업데이트")
    void findAndSave() {

        LocalBeefManagement localBeefManagement = localBeefManagementJpaRepository.findById(new LocalBeefManagementPK(entityNumber, identityNumber)).orElseThrow();
        CattleBuyInformation cattleBuyInformation = cattleBuyInformationJpaRepository.findById(new LocalBeefManagementPK(entityNumber, identityNumber)).orElseThrow();

        System.out.println("---------------------------------- 절취선 -------------------------------------------");
        cattleBuyInformation.setBuyNote("노트트트 수정");
        System.out.println(String.format("수정 내용 >> localBeefManagement.getCattleBuyInformation() :  %s", localBeefManagement.getCattleBuyInformation()));

        localBeefManagement.setCattleBuyInformation(cattleBuyInformation);
        localBeefManagementJpaRepository.save(localBeefManagement);

        System.out.println("end");
    }

    @Test
    @Order(4)
    @DisplayName("부모객체에 자식객체 추가하여 한번에 저장하기 : 영속성 전이 관계를 설정하여야만 동작 한다.")
    void localbeefAndCattleSave() {
        LocalBeefManagement localBeefManagement = new LocalBeefManagement();
        localBeefManagement.setLocalBeefManagementPK(new LocalBeefManagementPK("3333", "5555"));
        localBeefManagement.setBirthDay(LocalDate.now());
        localBeefManagement.setGender(GenderCode.FEMALE);

        CattleBuyInformation cattleBuyInformation = new CattleBuyInformation();
        cattleBuyInformation.setCattleBuyInformationPK(new LocalBeefManagementPK("3333", "5555"));
        cattleBuyInformation.setBuyDate(LocalDate.now());
        cattleBuyInformation.setBuyNote("노트 처음");

        cattleBuyInformation.setLocalBeefManagement(localBeefManagement);
        localBeefManagement.setCattleBuyInformation(cattleBuyInformation);

        this.localBeefManagementJpaRepository.save(localBeefManagement);

    }

    @Test
    @Order(5)
    @DisplayName("자식객체에 부모객체를 삽입하여 저장하기 : 영속성 전이 관계가 있어야만 작동된다.")
    void localbeefAndCattleSaveReverse() {

        LocalBeefManagement localBeefManagement = new LocalBeefManagement();
        localBeefManagement.setLocalBeefManagementPK(new LocalBeefManagementPK("4444", "5555"));
        localBeefManagement.setBirthDay(LocalDate.now());
        localBeefManagement.setGender(GenderCode.FEMALE);

        CattleBuyInformation cattleBuyInformation = new CattleBuyInformation();
        cattleBuyInformation.setCattleBuyInformationPK(new LocalBeefManagementPK("4444", "5555"));
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
            localBeefManagement.setLocalBeefManagementPK(new LocalBeefManagementPK("1111" + value, "2222"));
            localBeefManagement.setBirthDay(LocalDate.now());
            localBeefManagement.setGender(GenderCode.FEMALE);

            CattleBuyInformation cattleBuyInformation = new CattleBuyInformation();
            cattleBuyInformation.setCattleBuyInformationPK(new LocalBeefManagementPK("1111" + value, "2222"));
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
    @DisplayName("여러개 저장 후 레이지 로딩 동작 확인 - 자식 기준 단방향 맵핑시")
    @Order(7)
    public void saveManyChild_단방향() {
        List<CattleBuyInformation> cattleBuyInformations = new ArrayList<>();
        IntStream.range(0, 100).forEach(value -> {
            LocalBeefManagement localBeefManagement = new LocalBeefManagement();
            localBeefManagement.setLocalBeefManagementPK(new LocalBeefManagementPK("1111" + value, "2222"));
            localBeefManagement.setBirthDay(LocalDate.now());
            localBeefManagement.setGender(GenderCode.FEMALE);

            CattleBuyInformation cattleBuyInformation = new CattleBuyInformation();
            cattleBuyInformation.setCattleBuyInformationPK(new LocalBeefManagementPK("1111" + value, "2222"));
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

    @Test
    @DisplayName("레이지 로딩 동작 확인 - 부모기준 양방향 맵핑시")
    @Order(8)
    public void selectParent_양_단_방향() {
        System.out.println("-------------------------------- 조회 시작 ----------------------------");
        List<LocalBeefManagement> beefManagements = this.localBeefManagementJpaRepository.findAll();

        beefManagements.forEach(localBeefManagement -> {
            try {
                log.debug("beefManagement 구입기록 info : {} ", localBeefManagement.getCattleBuyInformation());
                log.debug("beefManagement 판매기록 info : {} ", localBeefManagement.getCattleSellStoreInformation());
            }catch (Exception e) {
                log.error("parent point of view .. ",e);
            }

        });
        log.debug("beef size = {}", beefManagements.size());

    }

    /**
     * lazy 로딩 안됨
     */
    @Test
    @DisplayName("레이지 로딩 동작 확인 - 자식 기준 단방향 맵핑시")
    @Order(9)
    public void selectChild_양_단방향() {
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