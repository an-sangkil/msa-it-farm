package com.skan.farm.repository.jooq;

import com.skan.farm.domain.model.ObservationDiary;
import com.skan.farm.paging.Page;
import com.skan.farm.paging.PageImpl;
import com.skan.farm.paging.Pageable;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <pre>
 * Description :
 *
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2020 by SKAN . All right reserved.
 * @since 2020-08-30
 */
@Repository
@RequiredArgsConstructor
public class ObserveJooqRepository {
    private DSLContext dslContext;
    private JdbcTemplate jdbcTemplate;

    public Page<List<ObservationDiary>> findByAll(ObservationDiary predicateObserve, Pageable pageable) {



        return new PageImpl<List<ObservationDiary>>(pageable, null,0);
    }
}
