package com.skan.farm.repository;

import com.skan.farm.domain.tables.JUsers;
import com.skan.farm.model.Users;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <pre>
 * Description :
 *
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2020 by CJENM|Mezzomedia. All right reserved.
 * @since 2020-06-16
 */
@Repository
@Slf4j
public class TestJooqRepository {

    private final DSLContext dslContext;
    private final JdbcTemplate jdbcTemplate;

    public TestJooqRepository(DSLContext dslContext, JdbcTemplate jdbcTemplate) {
        this.dslContext = dslContext;
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> getList() {
        String sql = dslContext.select(DSL.field("user_id"), DSL.field("user_name"))
                .from(DSL.table("users")).getSQL();
        log.debug("string sql  = {}", sql);
        return jdbcTemplate.queryForList(sql);
    }

    public List<Users> getListDSLType() {
        return dslContext.selectFrom(JUsers.USERS).fetchInto(Users.class);
    }


}
