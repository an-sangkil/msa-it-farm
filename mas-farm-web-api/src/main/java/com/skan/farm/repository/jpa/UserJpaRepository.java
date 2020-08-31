package com.skan.farm.repository.jpa;

import com.skan.farm.domain.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * <pre>
 * Description :
 *
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2020 by CJENM|Mezzomedia. All right reserved.
 * @since 2020-07-14
 */
public interface UserJpaRepository extends JpaRepository<Users, String> {


    Optional<Users> findByUserId(String userId);
}
