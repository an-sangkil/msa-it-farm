package com.skan.farm.repository.jooq;

import com.skan.farm.domain.entity.Vaccination;
import com.skan.farm.paging.PageableRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestConstructor;

/**
 * <pre>
 * Description :
 *
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2020 by CJENM|Mezzomedia. All right reserved.
 * @since 2020-09-08
 */
@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@ActiveProfiles("test")
@Slf4j
class VaccinationJooqRepositoryTest {

    final VaccinationJooqRepository vaccinationJooqRepository;

    VaccinationJooqRepositoryTest(VaccinationJooqRepository vaccinationJooqRepository) {
        this.vaccinationJooqRepository = vaccinationJooqRepository;
    }

    @Test
    void findByPaging() {

        Vaccination vaccination = new Vaccination();
        vaccinationJooqRepository.findByPaging(vaccination, new PageableRequest(0, 10));

    }
}