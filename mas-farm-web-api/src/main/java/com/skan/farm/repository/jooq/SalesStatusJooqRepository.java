package com.skan.farm.repository.jooq;

import com.skan.farm.domain.model.SalesStatus;
import com.skan.farm.domain.tables.JCattleSellStoreInformation;
import com.skan.farm.domain.tables.JLocalBeefManagement;
import com.skan.farm.model.LocalBeefManagement;
import com.skan.farm.model.Users;
import com.skan.farm.paging.PageImpl;
import com.skan.farm.paging.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

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
@Slf4j
@Repository
@RequiredArgsConstructor
public class SalesStatusJooqRepository {

    private final DSLContext dslContext;
    private final JdbcTemplate jdbcTemplate;

    public PageImpl<SalesStatus> findByAll(SalesStatus predicateSalesStatus, Pageable pageable) {

        JLocalBeefManagement jLocalBeefManagement = JLocalBeefManagement.LOCAL_BEEF_MANAGEMENT;
        JCattleSellStoreInformation jCattleSellStoreInformation = JCattleSellStoreInformation.CATTLE_SELL_STORE_INFORMATION;


        String sql = String.format("" +
                "select count(T1.entity_identification_number) from cattle_sell_store_information as T1 \n" +
                "left outer join local_beef_management as T2 ON (T1.entity_management_number = T2.entity_management_number AND T1.entity_identification_number = T2.entity_identification_number)" +
                //"where " +
                "");
        Integer totalCount = jdbcTemplate.queryForObject(sql,Integer.class);
        log.debug("totalCount = {}", totalCount);

        List<SalesStatus> salesStatuses = dslContext.select(
                jLocalBeefManagement.ENTITY_MANAGEMENT_NUMBER,
                jLocalBeefManagement.ENTITY_IDENTIFICATION_NUMBER,
                jCattleSellStoreInformation.asterisk()
        )
                .from(jCattleSellStoreInformation)
                .leftOuterJoin(jLocalBeefManagement)
                .on(
                        jCattleSellStoreInformation.ENTITY_IDENTIFICATION_NUMBER.eq(jLocalBeefManagement.ENTITY_IDENTIFICATION_NUMBER),
                        jCattleSellStoreInformation.ENTITY_MANAGEMENT_NUMBER.eq(jLocalBeefManagement.ENTITY_MANAGEMENT_NUMBER)
                )
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetchInto(SalesStatus.class);

        salesStatuses.forEach(salesStatus -> {
            log.debug("salesStatus = {}", salesStatus);
        });

        return new PageImpl<SalesStatus>(pageable, salesStatuses, totalCount);
    }

}
