package com.skan.farm.repository.jpa;

import com.skan.farm.model.Users;
import com.skan.farm.repository.TestCodeGeneration;
import com.skan.farm.repository.TestNonConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Repository;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

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
@Slf4j
@TestNonConfiguration
@RequiredArgsConstructor
class UserJpaRepositoryTest implements TestCodeGeneration {

    final UserJpaRepository userJpaRepository;

    @Override
    public void saveSelect() {

    }

    @Test
    @Override
    public void save() {
        Users user = Users.builder()
                .uuid(UUID.randomUUID().toString())
                .userId("skan")
                .build();
        userJpaRepository.save(user);

    }

    @Override
    public void select() {
        userJpaRepository.findAll();
    }

    @Override
    public void delete() {

    }
}