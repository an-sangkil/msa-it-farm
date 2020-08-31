package com.skan.farm.repository.jooq;

import com.skan.farm.code.GenderCode;
import com.skan.farm.domain.tables.JCalvesManagement;
import com.skan.farm.domain.tables.JCattleBuyInformation;
import com.skan.farm.domain.tables.JCattleSellStoreInformation;
import com.skan.farm.domain.tables.JLocalBeefManagement;
import com.skan.farm.domain.entity.CattleBuyInformation;
import com.skan.farm.domain.entity.CattleSellStoreInformation;
import com.skan.farm.domain.entity.LocalBeefManagement;
import com.skan.farm.domain.entity.LocalBeefManagementPK;
import com.skan.farm.paging.PageImpl;
import com.skan.farm.paging.Pageable;
import lombok.extern.slf4j.Slf4j;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.conf.ParamType;
import org.jooq.impl.DSL;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
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

    public PageImpl<LocalBeefManagement> findAll(LocalBeefManagement predicate, Pageable pageable) {

        JLocalBeefManagement jLocalBeefManagement = JLocalBeefManagement.LOCAL_BEEF_MANAGEMENT;
        JCattleBuyInformation jCattleBuyInformation = JCattleBuyInformation.CATTLE_BUY_INFORMATION;
        JCattleSellStoreInformation jCattleSellStoreInformation = JCattleSellStoreInformation.CATTLE_SELL_STORE_INFORMATION;
        JCalvesManagement jCalvesManagement = JCalvesManagement.CALVES_MANAGEMENT;


        // 전체 카운트
        String sql = dslContext.selectCount()
                .from(jLocalBeefManagement)
                .leftJoin(jCattleBuyInformation)
                .on(
                        jLocalBeefManagement.ENTITY_MANAGEMENT_NUMBER.eq(jCattleBuyInformation.ENTITY_MANAGEMENT_NUMBER),
                        jLocalBeefManagement.ENTITY_IDENTIFICATION_NUMBER.eq(jCattleBuyInformation.ENTITY_IDENTIFICATION_NUMBER))
                .where(this.dynamicConditionsLocalbeffManagement(predicate))
                .getSQL(ParamType.INLINED);


        log.debug("sql = : {}", sql);
        Integer totalCount = jdbcTemplate.queryForObject(sql, Integer.class);

        //'/?분만정보(수정횟수..etc)
        Field<String> calves = dslContext.select(
                DSL.concat(
                        jCalvesManagement.FERTILIZATION_INDEX, DSL.concat("@@")
                        , DSL.count(jCalvesManagement.FERTILIZATION_INDEX), DSL.concat("@@")
                        , DSL.max(jCalvesManagement.EXPECTED_DATE_CONFINEMENT)
                ))
                .from(jCalvesManagement).where(
                        jCalvesManagement.ENTITY_MANAGEMENT_NUMBER.eq(jLocalBeefManagement.ENTITY_MANAGEMENT_NUMBER),
                        jCalvesManagement.ENTITY_IDENTIFICATION_NUMBER.eq(jLocalBeefManagement.ENTITY_IDENTIFICATION_NUMBER),
                        jCalvesManagement.FERTILIZATION_INDEX.eq(dslContext.select(DSL.max(jCalvesManagement.FERTILIZATION_INDEX))
                                .from(jCalvesManagement)
                                .where(
                                        jCalvesManagement.ENTITY_MANAGEMENT_NUMBER.eq(jLocalBeefManagement.ENTITY_MANAGEMENT_NUMBER)
                                                .and(jCalvesManagement.ENTITY_IDENTIFICATION_NUMBER.eq(jLocalBeefManagement.ENTITY_IDENTIFICATION_NUMBER))
                                ))
                ).asField("calves_information");

        // 데이터 조회 SQL 생성
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
                , jLocalBeefManagement.ROOM_NUMBER
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
                , calves

        )
                .from(jLocalBeefManagement)
                .leftJoin(jCattleBuyInformation)
                .on(
                        jLocalBeefManagement.ENTITY_MANAGEMENT_NUMBER.eq(jCattleBuyInformation.ENTITY_MANAGEMENT_NUMBER),
                        jLocalBeefManagement.ENTITY_IDENTIFICATION_NUMBER.eq(jCattleBuyInformation.ENTITY_IDENTIFICATION_NUMBER))
                .leftJoin(

                        jCattleSellStoreInformation
                ).on(
                        jLocalBeefManagement.ENTITY_MANAGEMENT_NUMBER.eq(jCattleSellStoreInformation.ENTITY_IDENTIFICATION_NUMBER),
                        jLocalBeefManagement.ENTITY_IDENTIFICATION_NUMBER.eq(jCattleSellStoreInformation.ENTITY_IDENTIFICATION_NUMBER)
                )
                .where(this.dynamicConditionsLocalbeffManagement(predicate))
                .orderBy(jLocalBeefManagement.CREATED_TIME.desc())
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .getSQL(ParamType.INLINED);


        // 데이터 조회 및 매핑
        log.debug("sql = : {}", sql);
        List<LocalBeefManagement> localBeefManagements = new ArrayList<>();
        jdbcTemplate.queryForList(sql)
                .forEach(stringObjectMap -> {

                    String ENTITY_MANAGEMENT_NUMBER = (String) stringObjectMap.get("ENTITY_MANAGEMENT_NUMBER");
                    String ENTITY_IDENTIFICATION_NUMBER = (String) stringObjectMap.get("ENTITY_IDENTIFICATION_NUMBER");
                    LocalBeefManagement localBeefManagement = LocalBeefManagement
                            .builder()
                            .localBeefManagementPK(new LocalBeefManagementPK(ENTITY_IDENTIFICATION_NUMBER, ENTITY_MANAGEMENT_NUMBER))
                            .birthDay(stringObjectMap.get("BIRTH_DAY") != null ? ((java.sql.Date) stringObjectMap.get("BIRTH_DAY")).toLocalDate() : null)
                            .earTagDate(stringObjectMap.get("EAR_TAG_DATE") != null ? ((java.sql.Date) stringObjectMap.get("EAR_TAG_DATE")).toLocalDate() : null)
                            .enterDate(stringObjectMap.get("ENTER_DATE") != null ? ((java.sql.Date) stringObjectMap.get("ENTER_DATE")).toLocalDate() : null)
                            .parentMomNo((String) stringObjectMap.get("PARENT_MOM_NO"))
                            .parentPapaNo((String) stringObjectMap.get("PARENT_PAPA_NO"))
                            .sellYn((String) stringObjectMap.get("SELL_YN"))
                            .gender(stringObjectMap.get("GENDER") != null ? GenderCode.valueOf(stringObjectMap.get("GENDER").toString()) : GenderCode.MALE)
                            .location((String) stringObjectMap.get("ROOM_NUMBER"))
                            .createdTime(stringObjectMap.get("CREATED_TIME") != null ? ((java.sql.Timestamp) stringObjectMap.get("CREATED_TIME")).toLocalDateTime() : null)
                            .build();

                    if (stringObjectMap.get("calves_information") != null) {
                        String[] calvesInformation = ((String) stringObjectMap.get("calves_information")).split("@@");
                        localBeefManagement.setCalvesCount(calvesInformation[0]);
                        localBeefManagement.setInseminationCount(calvesInformation[1]);
                        localBeefManagement.setExpectedDateChildbirth(LocalDateTime.parse(calvesInformation[2], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

                    }

                    if (localBeefManagement.getBirthDay() != null) {
                        Period period = Period.between(localBeefManagement.getBirthDay(), LocalDate.now());
                        localBeefManagement.setNumberOfMonth(String.valueOf(period.getMonths()));
                    }


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

        return new PageImpl<LocalBeefManagement>(pageable, localBeefManagements, totalCount);

    }

    protected List<Condition> dynamicConditionsLocalbeffManagement(LocalBeefManagement predicate) {
        List<Condition> conditions = new ArrayList<>();
        JLocalBeefManagement jLocalBeefManagement = JLocalBeefManagement.LOCAL_BEEF_MANAGEMENT;
        JCattleBuyInformation jCattleBuyInformation = JCattleBuyInformation.CATTLE_BUY_INFORMATION;
        JCattleSellStoreInformation jCattleSellStoreInformation = JCattleSellStoreInformation.CATTLE_SELL_STORE_INFORMATION;

        //if (!StringUtils.isEmpty(predicate.getLocalBeefManagementPK().getEntityManagementNumber())) {
        //    conditions.add(jLocalBeefManagement.ENTITY_MANAGEMENT_NUMBER.eq(predicate.getLocalBeefManagementPK().getEntityManagementNumber()));
        //}

        if (!StringUtils.isEmpty(predicate.getLocalBeefManagementPK().getEntityIdentificationNumber())) {
            conditions.add(jLocalBeefManagement.ENTITY_IDENTIFICATION_NUMBER.like("%" + predicate.getLocalBeefManagementPK().getEntityIdentificationNumber() + "%"));
        }

        if (!StringUtils.isEmpty(predicate.getLocation())) {
            conditions.add(jLocalBeefManagement.ROOM_NUMBER.like("%" + predicate.getLocation() + "%"));
        }

        if (!StringUtils.isEmpty(predicate.getBetweenBirthDate())) {

            String[] betweenBirthDate = predicate.getBetweenBirthDate().split("@@");
            LocalDate startDate = LocalDate.parse(betweenBirthDate[0],DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate endDate = LocalDate.parse(betweenBirthDate[1],DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            conditions.add(jLocalBeefManagement.BIRTH_DAY.between(startDate, endDate.plusDays(1)));
        }

        if (!StringUtils.isEmpty(predicate.getGender())) {
            conditions.add(jLocalBeefManagement.GENDER.eq(predicate.getGender().name()));
        }

        return conditions;
    }
}
