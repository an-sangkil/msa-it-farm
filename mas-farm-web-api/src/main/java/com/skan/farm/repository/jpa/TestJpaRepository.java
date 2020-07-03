package com.skan.farm.repository.jpa;

import com.skan.farm.model.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * <pre>
 * Description :
 *
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2020 by CJENM|Mezzomedia. All right reserved.
 * @since 2020-07-03
 */

public interface TestJpaRepository extends CrudRepository<Users,String> {

}
