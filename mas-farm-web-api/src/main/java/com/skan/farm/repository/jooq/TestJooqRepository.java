package com.skan.farm.repository.jooq;

import com.skan.farm.domain.tables.JUsers;
import com.skan.farm.domain.entity.Users;
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

    /**
     * jooq로 표쥰 sql 생성후 jdbctemplate 을 사용하여 데이터 불러오기
     *
     * @return
     */
    public List<Map<String, Object>> getList() {
        String sql = dslContext.select(DSL.field("user_id"), DSL.field("user_name"))
                .from(DSL.table("users")).getSQL();
        log.debug("string sql  = {}", sql);
        return jdbcTemplate.queryForList(sql);
    }

    /**
     * jooq로 데이터를 가져와 domain 객체로 바꿔주기
     *
     * @return
     */
    public List<Users> getListObject() {
        return dslContext.select(DSL.field("user_id"), DSL.field("user_name"))
                .from(DSL.table("users")).fetchInto(Users.class);
    }

    public List<Users> getListDSLType() {
        return dslContext.selectFrom(JUsers.USERS).fetchInto(Users.class);
    }

    public void insert(Users users) {
        JUsers jUsers = JUsers.USERS;
        dslContext.insertInto(jUsers)
                .set(jUsers.UUID, users.getUuid())
                .set(jUsers.USER_ID, users.getUserId())
                .set(jUsers.USER_NAME, users.getUserName())
                .set(jUsers.PASSWORD, users.getPassword())
                .execute();
    }

    public void update(Users users) {
        JUsers jUsers = JUsers.USERS;
        int updateRecords = dslContext.update(jUsers)
                .set(jUsers.USER_NAME, users.getUserName())
                .where(jUsers.USER_ID.equal(users.getUuid()))
                .execute();

    }

    public void delete(String key) {
        int deleteRecords = dslContext.delete(JUsers.USERS).where(JUsers.USERS.USER_ID.equal(key)).execute();
        log.debug("deleteRecords : {}", deleteRecords);
    }

}
