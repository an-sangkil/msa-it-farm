package com.skan.farm.repository.jooq;

import com.skan.farm.domain.tables.JCattleBuyInformation;
import com.skan.farm.domain.tables.JCattleSellStoreInformation;
import com.skan.farm.domain.tables.JLocalBeefManagement;
import com.skan.farm.model.LocalBeefManagement;
import com.skan.farm.model.LocalBeefManagementPK;
import com.skan.farm.paging.Page;
import com.skan.farm.paging.PageableDefault;
import com.skan.farm.paging.PageableJooq;
import com.skan.farm.paging.PageableJpa;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

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
@Repository
public class LocalBeefManagementJooqRepository {

    private final DSLContext dslContext;
    private final JdbcTemplate jdbcTemplate;

    public LocalBeefManagementJooqRepository(DSLContext dslContext, JdbcTemplate jdbcTemplate) {
        this.dslContext = dslContext;
        this.jdbcTemplate = jdbcTemplate;
    }

    public Page<LocalBeefManagement> findAll(LocalBeefManagement predicate, PageableDefault pageableDefault) {

        JLocalBeefManagement jLocalBeefManagement = JLocalBeefManagement.LOCAL_BEEF_MANAGEMENT;
        JCattleBuyInformation jCattleBuyInformation = JCattleBuyInformation.CATTLE_BUY_INFORMATION;
        JCattleSellStoreInformation jCattleSellStoreInformation = JCattleSellStoreInformation.CATTLE_SELL_STORE_INFORMATION;


        // TODO  LIMIT 없는 전체 TOTAL 데이터 구하기 필요
        String sql = dslContext.select(jLocalBeefManagement.BIRTH_DAY)
                .from(jLocalBeefManagement)
                .join(jCattleBuyInformation)
                .on(
                        jLocalBeefManagement.ENTITY_MANAGEMENT_NUMBER.eq(jCattleBuyInformation.ENTITY_MANAGEMENT_NUMBER),
                        jLocalBeefManagement.ENTITY_IDENTIFICATION_NUMBER.eq(jCattleBuyInformation.ENTITY_IDENTIFICATION_NUMBER))
                //.where("","")
                .limit(0)
                .offset(1)
                .getSQL();


        log.debug("sql = : {}", sql);
        List<LocalBeefManagement> localBeefManagements = new ArrayList<>();
        jdbcTemplate.queryForList(sql,pageableDefault.getPageSize(),pageableDefault.getOffset())
                .forEach(stringObjectMap -> {

                    String ENTITY_MANAGEMENT_NUMBER = (String) stringObjectMap.get("ENTITY_MANAGEMENT_NUMBER");
                    String ENTITY_IDENTIFICATION_NUMBER = (String) stringObjectMap.get("ENTITY_IDENTIFICATION_NUMBER");
                    LocalBeefManagement localBeefManagement = LocalBeefManagement
                            .builder()
                            .localBeefManagementPK(new LocalBeefManagementPK(ENTITY_MANAGEMENT_NUMBER, ENTITY_IDENTIFICATION_NUMBER)).build();

                    localBeefManagements.add(localBeefManagement);
                });

        return new Page<LocalBeefManagement>(pageableDefault,localBeefManagements);

    }
}
