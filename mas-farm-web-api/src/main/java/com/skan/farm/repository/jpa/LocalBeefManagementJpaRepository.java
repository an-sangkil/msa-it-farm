package com.skan.farm.repository.jpa;

import com.skan.farm.domain.entity.LocalBeefManagement;
import com.skan.farm.domain.entity.LocalBeefManagementPK;
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
 * @since 2020-07-06
 */
public interface LocalBeefManagementJpaRepository extends JpaRepository<LocalBeefManagement, LocalBeefManagementPK>
//, QuerydslPredicateExecutor<LocalBeefManagement>
{
}
