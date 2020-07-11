package com.skan.farm.repository.jooq;

import com.skan.farm.model.LocalBeefManagement;
import com.skan.farm.paging.Page;
import com.skan.farm.paging.PageableJooq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestConstructor;

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
 * @since 2020-07-10
 */
@Slf4j
@SpringBootTest
@ActiveProfiles("test")
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class LocalBeefManagementJooqRepositoryTest {

    final LocalBeefManagementJooqRepository localBeefManagementJooqRepository;

    @Test
    void findAll() {
        LocalBeefManagement localBeefManagement = new LocalBeefManagement();
        PageableJooq pageableJooq = new PageableJooq(0, 10);
        Page<LocalBeefManagement> paging = localBeefManagementJooqRepository.findAll(localBeefManagement, pageableJooq);
        log.debug("paging = {}", paging);

    }
}