package com.skan.farm.repository.jooq;

import com.skan.farm.model.LocalBeefManagement;
import com.skan.farm.paging.PageImpl;
import com.skan.farm.paging.PageableRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestConstructor;

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
        PageableRequest pageableRequest = new PageableRequest(0, 10);
        PageImpl<LocalBeefManagement> paging = localBeefManagementJooqRepository.findAll(localBeefManagement, pageableRequest);
        log.debug("paging = {}", paging);

    }
}