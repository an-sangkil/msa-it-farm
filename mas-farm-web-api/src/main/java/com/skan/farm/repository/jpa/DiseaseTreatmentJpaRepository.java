package com.skan.farm.repository.jpa;

import com.skan.farm.model.DiseaseTreatment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * <pre>
 * Description :
 *
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2020 by CJENM|Mezzomedia. All right reserved.
 * @since 2020-07-08
 */
public interface DiseaseTreatmentJpaRepository extends JpaRepository<DiseaseTreatment, DiseaseTreatment.DiseaseTreatmentPK> {

    List<DiseaseTreatment> findByDiseaseTreatmentPK_EntityManagementNumberAndDiseaseTreatmentPK_EntityIdentificationNumber(String entityId, String identityId);
}
