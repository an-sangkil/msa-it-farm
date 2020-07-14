package com.skan.farm.repository.jooq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.conf.ParamType;
import org.jooq.impl.DSL;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
@RequiredArgsConstructor
@Slf4j
@Repository
public class CalvesManagementJooqRepository {
    private final DSLContext dslContext;
    private final JdbcTemplate jdbcTemplate;

    /**
     * 마지막 번호 리턴
     * @param entityId
     * @param identityId
     * @return
     */
    public int findByLastCount(String entityId, String identityId) {

        String sql = dslContext.selectCount().from(DSL.table("calves_management"))
                .where(DSL.field("entity_management_number").eq(entityId))
                .and(DSL.field("entity_identification_number").eq(identityId))
                .getSQL(ParamType.NAMED_OR_INLINED);

        log.debug("query = {}", sql);
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        log.debug("last count = {}", count);

        return count;

    }

    /**
     * 회차 정보 조회  -> 성공후 마지막 카운트 리턴
     * @param entityId
     * @param identityId
     * @return
     */
    public int findByFertilizationIndex(String entityId, String identityId) {
        String sql = dslContext.selectCount().from(DSL.table("calves_management"))
                .where(DSL.field("entity_management_number").eq(entityId))
                .and(DSL.field("entity_identification_number").eq(identityId))
                .and(DSL.field("calving_date").isNotNull())
                .getSQL(ParamType.NAMED_OR_INLINED);

        log.debug("query = {}", sql);
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        log.debug("Fertilization Index count = {}", count);

        return count;
    }
}
