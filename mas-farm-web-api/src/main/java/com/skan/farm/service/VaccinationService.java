package com.skan.farm.service;

import com.skan.farm.domain.entity.Vaccination;
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
 * @version Copyright (C) 2020 by CJENM|Mezzomedia. All right reserved.
 * @since 2020-09-07
 */
@Service
@RequiredArgsConstructor
public class VaccinationService {

    VaccinationJpaRepository vaccinationJpaRepository;

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
