package com.skan.farm.repository.jooq;

import com.skan.farm.code.GenderCode;
import com.skan.farm.domain.tables.JCattleBuyInformation;
import com.skan.farm.domain.tables.JCattleSellStoreInformation;
import com.skan.farm.domain.tables.JLocalBeefManagement;
import com.skan.farm.model.CattleBuyInformation;
import com.skan.farm.model.CattleSellStoreInformation;
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

import java.time.LocalDate;
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


        String sql = dslContext.selectCount()
                .from(jLocalBeefManagement)
                .join(jCattleBuyInformation)
                .on(
                        jLocalBeefManagement.ENTITY_MANAGEMENT_NUMBER.eq(jCattleBuyInformation.ENTITY_MANAGEMENT_NUMBER),
                        jLocalBeefManagement.ENTITY_IDENTIFICATION_NUMBER.eq(jCattleBuyInformation.ENTITY_IDENTIFICATION_NUMBER))
                //.where("","")
                .getSQL();
        log.debug("sql = : {}", sql);
        int totalCount = jdbcTemplate.queryForObject(sql, Integer.class);


        sql = dslContext.select(
                jLocalBeefManagement.BIRTH_DAY
                , jLocalBeefManagement.CASTRATION_DATE
                , jLocalBeefManagement.CREATED_TIME
                , jLocalBeefManagement.EAR_TAG_DATE
                , jLocalBeefManagement.ENTER_DATE
                , jLocalBeefManagement.ENTITY_IDENTIFICATION_NUMBER
                , jLocalBeefManagement.ENTITY_MANAGEMENT_NUMBER
                , jLocalBeefManagement.PARENT_MOM_NO
                , jLocalBeefManagement.PARENT_PAPA_NO
                , jLocalBeefManagement.GENDER
                , jLocalBeefManagement.SELL_YN
                , jCattleBuyInformation.BUY_DATE
                , jCattleBuyInformation.BUY_NOTE
                , jCattleBuyInformation.BUY_PHONE_NUMBER
                , jCattleBuyInformation.BUY_SELL_PHONE_NUMBER
                , jCattleBuyInformation.BUY_STORE_NAME
                , jCattleSellStoreInformation.BEEF_GRADE
                , jCattleSellStoreInformation.DRESSED_WEIGHT
                , jCattleSellStoreInformation.LIVEWEIGHT
                , jCattleSellStoreInformation.SELL_DATE
                , jCattleSellStoreInformation.SELL_NOTE
                , jCattleSellStoreInformation.SELL_PHONE_NUMBER
        )
                .from(jLocalBeefManagement)
                .join(jCattleBuyInformation)
                .on(
                        jLocalBeefManagement.ENTITY_MANAGEMENT_NUMBER.eq(jCattleBuyInformation.ENTITY_MANAGEMENT_NUMBER),
                        jLocalBeefManagement.ENTITY_IDENTIFICATION_NUMBER.eq(jCattleBuyInformation.ENTITY_IDENTIFICATION_NUMBER))
                .leftJoin(

                        jCattleSellStoreInformation
                ).on(
                        jLocalBeefManagement.ENTITY_MANAGEMENT_NUMBER.eq(jCattleSellStoreInformation.ENTITY_IDENTIFICATION_NUMBER),
                        jLocalBeefManagement.ENTITY_IDENTIFICATION_NUMBER.eq(jCattleSellStoreInformation.ENTITY_IDENTIFICATION_NUMBER)
                )
                //.where("","")
                .limit(pageableDefault.getPageSize())
                .offset(pageableDefault.getOffset())
                .getSQL();


        log.debug("sql = : {}", sql);
        List<LocalBeefManagement> localBeefManagements = new ArrayList<>();
        jdbcTemplate.queryForList(sql, pageableDefault.getPageSize(), pageableDefault.getOffset())
                .forEach(stringObjectMap -> {

                    String ENTITY_MANAGEMENT_NUMBER = (String) stringObjectMap.get("ENTITY_MANAGEMENT_NUMBER");
                    String ENTITY_IDENTIFICATION_NUMBER = (String) stringObjectMap.get("ENTITY_IDENTIFICATION_NUMBER");
                    LocalBeefManagement localBeefManagement = LocalBeefManagement
                            .builder()
                            .localBeefManagementPK(new LocalBeefManagementPK(ENTITY_MANAGEMENT_NUMBER, ENTITY_IDENTIFICATION_NUMBER))
                            .birthDay(((java.sql.Date) stringObjectMap.get("BIRTH_DAY")).toLocalDate())
                            .earTagDate(stringObjectMap.get("EAR_TAG_DATE") != null ? ((java.sql.Date) stringObjectMap.get("EAR_TAG_DATE")).toLocalDate() : null)
                            .enterDate(stringObjectMap.get("ENTER_DATE") != null ? ((java.sql.Date) stringObjectMap.get("ENTER_DATE")).toLocalDate() : null)
                            .parentMomNo((String) stringObjectMap.get("PARENT_MOM_NO"))
                            .parentPapaNo((String) stringObjectMap.get("PARENT_PAPA_NO"))
                            .sellYn((String) stringObjectMap.get("SELL_YN"))
                            .gender(stringObjectMap.get("GENDER") != null ? GenderCode.valueOf(stringObjectMap.get("GENDER").toString()) : GenderCode.MALE)
                            .createdTime(((java.sql.Date) stringObjectMap.get("CREATED_TIME")))
                            .build();

                    CattleBuyInformation cattleBuyInformation = CattleBuyInformation.builder()
                            .cattleBuyInformationPK(new LocalBeefManagementPK(ENTITY_MANAGEMENT_NUMBER, ENTITY_IDENTIFICATION_NUMBER))
                            .buyDate(stringObjectMap.get("BUY_DATE") != null ? ((java.sql.Date) stringObjectMap.get("BUY_DATE")).toLocalDate() : null)
                            .buyNote((String) stringObjectMap.get("BUY_NOTE"))
                            .buyPhoneNumber((String) stringObjectMap.get("BUY_PHONE_NUMBER"))
                            .buySellPhoneNumber((String) stringObjectMap.get("BUY_SELL_PHONE_NUMBER"))
                            .buyStoreName((String) stringObjectMap.get("BUY_STORE_NAME"))
                            .build();

                    CattleSellStoreInformation cattleSellStoreInformation = CattleSellStoreInformation.builder()
                            .localBeefManagementPK(new LocalBeefManagementPK(ENTITY_MANAGEMENT_NUMBER, ENTITY_IDENTIFICATION_NUMBER))
                            .beefGrade((String) stringObjectMap.get("BEEF_GRADE"))
                            .dressedWeight((Short) stringObjectMap.get("DRESSED_WEIGHT"))
                            .liveweight((Short) stringObjectMap.get("LIVEWEIGHT"))
                            .sellDate(stringObjectMap.get("SELL_DATE") != null ? ((java.sql.Date) stringObjectMap.get("SELL_DATE")).toLocalDate() : null)
                            .sellNote((String) stringObjectMap.get("SELL_NOTE"))
                            .sellPhoneNumber((String) stringObjectMap.get("SELL_PHONE_NUMBER"))
                            .build();


                    localBeefManagement.setCattleBuyInformation(cattleBuyInformation);
                    localBeefManagement.setCattleSellStoreInformation(cattleSellStoreInformation);

                    localBeefManagements.add(localBeefManagement);
                });

        return new Page<LocalBeefManagement>(pageableDefault, localBeefManagements, totalCount);

    }
}
