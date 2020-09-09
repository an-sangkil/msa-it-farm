package com.skan.farm.repository.jooq;

import com.skan.farm.domain.model.ObservationDiary;
import com.skan.farm.paging.PageImpl;
import com.skan.farm.paging.PageableRequest;
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
 * @since 2020-09-08
 */
@Slf4j
@SpringBootTest
@ActiveProfiles("test")
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class ObserveJooqRepositoryTest {

    final ObserveJooqRepository observeJooqRepository;

    @Test
    void findByAll() {

        ObservationDiary predicate = new ObservationDiary();
        predicate.setAgeOfMonth((short) 10);
        PageImpl<ObservationDiary> observationDiaryPage = (PageImpl<ObservationDiary>) observeJooqRepository.findByAll(predicate,new PageableRequest(0,100));
        log.debug("observationDiaryPage = {}", observationDiaryPage);
    }
}