package com.skan.farm.repository.jpa;

import com.skan.farm.code.GenderCode;
import com.skan.farm.domain.entity.DiseaseDetail;
import com.skan.farm.domain.entity.DiseaseTreatment;
import com.skan.farm.domain.entity.LocalBeefManagement;
import com.skan.farm.domain.entity.LocalBeefManagementPK;
import com.skan.farm.repository.TestNonConfiguration;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

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
@TestNonConfiguration
@RequiredArgsConstructor
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DiseaseDetailJpaRepositoryTest {

    final DiseaseDetailJpaRepository diseaseDetailJpaRepository;
    final LocalBeefManagementJpaRepository localBeefManagementJpaRepository;
    final DiseaseTreatmentJpaRepository diseaseTreatmentJpaRepository;

    String identityNumber = "1111";
    String entityNumber = "2222";
    LocalDate toDay = LocalDate.now();

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    @DisplayName("치료 내역 저장")
    void save() {

        LocalBeefManagement localBeefManagement = new LocalBeefManagement();
        localBeefManagement.setLocalBeefManagementPK(new LocalBeefManagementPK(identityNumber, entityNumber));
        localBeefManagement.setBirthDay(LocalDate.now());
        localBeefManagement.setGender(GenderCode.FEMALE);
        localBeefManagementJpaRepository.save(localBeefManagement);

        List<DiseaseTreatment> diseaseTreatments = new ArrayList<>();
        IntStream.rangeClosed(0, 3).forEach(value -> {
            DiseaseTreatment diseaseTreatment1 = new DiseaseTreatment();
            diseaseTreatment1.setDiseaseTreatmentPK(new DiseaseTreatment.DiseaseTreatmentPK(identityNumber, entityNumber, toDay.plusDays(value)));
            diseaseTreatment1.setDiseaseName("감기" + value);
            diseaseTreatment1.setCureDate(LocalDate.now().plusDays(value));
            diseaseTreatment1.setRemark("비고" + value);
            diseaseTreatment1.setCureAgeOfMonth((short) (10 + value));
            diseaseTreatment1.setSymptom("기침 콧물" + value);
            diseaseTreatment1.setTreatmentDetails("상세 정보..." + value);
            diseaseTreatments.add(diseaseTreatment1);
        });
        diseaseTreatmentJpaRepository.saveAll(diseaseTreatments);

        List<DiseaseDetail> diseaseDetails = new ArrayList<>();
        IntStream.rangeClosed(1, 10).forEach(value -> {
            DiseaseDetail diseaseDetail = DiseaseDetail.builder()
                    .diseaseDetailPK(new DiseaseDetail.DiseaseDetailPK(identityNumber, entityNumber, toDay, (short) value))
                    .medicationName("타이로펜" + value)
                    .injectionMethod("근육, 피하" + value)
                    .needleLoseYn("N")
                    .withdrawalPeriodExpirationDate(LocalDate.now().plusDays(3))
                    .build();
            diseaseDetails.add(diseaseDetail);
        });

        diseaseDetailJpaRepository.saveAll(diseaseDetails);

    }

}