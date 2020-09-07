package com.skan.farm.repository.jpa;

import com.skan.farm.domain.entity.Vaccination;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

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
public interface VaccinationJpaRepository extends JpaRepository<Vaccination, Vaccination.VaccinationPK> {

    public long countByVaccinationPKEntityIdentificationNumberAndVaccinationPKDay(String identityNumber, LocalDate day);
}
