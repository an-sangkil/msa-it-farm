package com.skan.farm.service;

import com.skan.farm.domain.entity.Vaccination;
import com.skan.farm.paging.Page;
import com.skan.farm.paging.Pageable;
import com.skan.farm.repository.jooq.VaccinationJooqRepository;
import com.skan.farm.repository.jpa.VaccinationJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * Description :
 *
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2020 by skan. All right reserved.
 * @since 2020-09-07
 */
@Service
@RequiredArgsConstructor
public class VaccinationService {

    final VaccinationJpaRepository vaccinationJpaRepository;
    final VaccinationJooqRepository vaccinationJooqRepository;

    public Page<Vaccination> vaccinationPage (Vaccination predicateVaccination, Pageable pageable) {
        return vaccinationJooqRepository.findByPaging(predicateVaccination,pageable);
    }

    public Vaccination save(Vaccination vaccination) {


        vaccinationJpaRepository.findById(vaccination.getVaccinationPK())
                .ifPresentOrElse(vaccination1 -> {
                            // 업데이트
                            vaccinationJpaRepository.save(vaccination);

                        }

                        , () -> {
                            // 신규
                            long count = vaccinationJpaRepository.countByVaccinationPKEntityIdentificationNumberAndVaccinationPKDay(vaccination.getVaccinationPK().getEntityIdentificationNumber(), vaccination.getVaccinationPK().getDay());
                            vaccination.getVaccinationPK().setSeq((short) (count+1));

                            vaccinationJpaRepository.save(vaccination);
                        });


        return vaccination;
    }
}
