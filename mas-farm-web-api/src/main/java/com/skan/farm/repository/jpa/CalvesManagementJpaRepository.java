package com.skan.farm.repository.jpa;

import com.skan.farm.domain.entity.CalvesManagement;
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
public interface CalvesManagementJpaRepository extends JpaRepository<CalvesManagement, CalvesManagement.CalvesManagementPK> {

    List<CalvesManagement> findByCalvesManagementPK_EntityManagementNumberAndCalvesManagementPK_EntityIdentificationNumber(String entityId, String indentId);
}
