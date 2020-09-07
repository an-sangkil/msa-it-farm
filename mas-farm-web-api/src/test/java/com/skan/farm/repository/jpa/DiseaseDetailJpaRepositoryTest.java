package com.skan.farm.repository.jpa;

import com.skan.farm.domain.entity.DiseaseDetail;
import com.skan.farm.domain.entity.DiseaseTreatment;
import com.skan.farm.repository.TestNonConfiguration;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.*;

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
 * @since 2020-09-07
 */
@TestNonConfiguration
@RequiredArgsConstructor
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DiseaseDetailJpaRepositoryTest {

    final DiseaseDetailJpaRepository diseaseDetailJpaRepository;

    String entityNumber = "1111";
    String identityNumber = "2222";
    final LocalDate toDay  = LocalDate.now();

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    @DisplayName("지료 내역 저장")
    void save() {

               DiseaseDetail diseaseDetail = DiseaseDetail.builder()
                .diseaseDetailPK(new DiseaseDetail.DiseaseDetailPK(identityNumber,entityNumber,toDay, (short)1))
                .medicationName("타이로펜")
                .build();

        diseaseDetailJpaRepository.save(diseaseDetail);

    }

}