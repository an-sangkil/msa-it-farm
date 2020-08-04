package com.skan.farm.service;

import com.skan.farm.model.DiseaseTreatment;
import com.skan.farm.repository.jpa.DiseaseTreatmentJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <pre>
 * Description :
 *
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2020 by CJENM|Mezzomedia. All right reserved.
 * @since 2020-08-04
 */

@Service
public class DiseaseTreatmentService {

    private final DiseaseTreatmentJpaRepository diseaseTreatmentJpaRepository;

    public DiseaseTreatmentService(DiseaseTreatmentJpaRepository diseaseTreatmentJpaRepository) {
        this.diseaseTreatmentJpaRepository = diseaseTreatmentJpaRepository;
    }

    public DiseaseTreatment save (DiseaseTreatment diseaseTreatment) {

        if (StringUtils.isEmpty(diseaseTreatment.getDiseaseTreatmentPK().getSeq())) {
            long count = diseaseTreatmentJpaRepository.countByDiseaseTreatmentPK_EntityManagementNumberAndDiseaseTreatmentPK_EntityIdentificationNumber(diseaseTreatment.getDiseaseTreatmentPK().getEntityManagementNumber(),diseaseTreatment.getDiseaseTreatmentPK().getEntityIdentificationNumber());
            diseaseTreatment.getDiseaseTreatmentPK().setSeq((short) (count+1));
        }
        diseaseTreatmentJpaRepository.save(diseaseTreatment);

        return diseaseTreatment;
    }
}
