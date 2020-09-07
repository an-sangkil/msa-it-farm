package com.skan.farm.repository.jpa;

import com.skan.farm.domain.entity.DiseaseDetail;
import org.springframework.data.jpa.repository.JpaRepository;

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
public interface DiseaseDetailJpaRepository extends JpaRepository<DiseaseDetail, DiseaseDetail.DiseaseDetailPK> {
}
