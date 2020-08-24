package com.skan.farm.repository.jooq;

import com.skan.farm.domain.model.SalesStatus;
import com.skan.farm.paging.PageImpl;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * <pre>
 * Description : 판매현황
 *
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2020 by SKAN . All right reserved.
 * @since 2020-08-24
 */
@Repository
@RequiredArgsConstructor
public class SalesStatusJooqRepository {

    private final DSLContext dslContext;
    private final JdbcTemplate jdbcTemplate;

    public PageImpl<SalesStatus> findByAll(SalesStatus predicateSalesStatus) {

        
        return null;
    }

}
