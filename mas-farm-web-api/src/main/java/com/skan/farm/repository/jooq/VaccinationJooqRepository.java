package com.skan.farm.repository.jooq;

import com.skan.farm.domain.entity.Vaccination;
import com.skan.farm.paging.PageImpl;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * <pre>
 * Description : 백신관리
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
public class VaccinationJooqRepository {

    private final DSLContext dslContext;
    private final JdbcTemplate jdbcTemplate;

    public PageImpl<Vaccination> findByAll(Vaccination predicateVaccination) {

        return null;
    }
}
