package com.skan.farm.repository.jpa;

import com.skan.farm.model.Users;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

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
@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@ActiveProfiles("test")
class TestJpaRepositoryTest {

    TestJpaRepository testJpaRepository;

    public TestJpaRepositoryTest(TestJpaRepository testJpaRepository) {
        this.testJpaRepository = testJpaRepository;
    }

    /**
     *  테스트 수행후 롤백이 일어나길 원한다면 @Transactional 선언 후 @Rollback(true) 해준다.
     *  기본적으로 유닛테스트에서 @Transaction 사용시 롤백이 일어나며 @Rollback(false) 로 해주면
     *  데이터는 저장되지 않는다.
     */
    @Test
    @Transactional
    @Rollback(true)
    void testInsert(){
        Users user =  new Users();
        user.setUuid(UUID.randomUUID().toString());
        user.setUserId("skan");
        user.setUserName("skan");
        user.setPassword("password");

        testJpaRepository.save(user);
    }

    @Test
    void testNonRollbackInsert(){
        Users user =  new Users();
        user.setUuid(UUID.randomUUID().toString());
        user.setUserId("skan");
        user.setUserName("skan");
        user.setPassword("password");

        testJpaRepository.save(user);
    }


    @Test
    void list(){
        testJpaRepository.findAll();
    }




}