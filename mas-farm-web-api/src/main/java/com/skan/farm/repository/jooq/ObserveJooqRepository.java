package com.skan.farm.repository.jooq;

import com.skan.farm.domain.model.ObservationDiary;
import com.skan.farm.domain.tables.JDiseaseDetail;
import com.skan.farm.domain.tables.JDiseaseTreatment;
import com.skan.farm.domain.tables.JLocalBeefManagement;
import com.skan.farm.paging.Page;
import com.skan.farm.paging.PageImpl;
import com.skan.farm.paging.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

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
 * @version Copyright (C) 2020 by SKAN . All right reserved.
 * @since 2020-08-30
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class ObserveJooqRepository {
    private final DSLContext dslContext;
    private final JdbcTemplate jdbcTemplate;

    public Page<ObservationDiary> findByAll(ObservationDiary predicateObserve, Pageable pageable) {

        JDiseaseTreatment jDiseaseTreatment = JDiseaseTreatment.DISEASE_TREATMENT;
        JDiseaseDetail jDiseaseDetail = JDiseaseDetail.DISEASE_DETAIL;
        JLocalBeefManagement jLocalBeefManagement = JLocalBeefManagement.LOCAL_BEEF_MANAGEMENT;

        int total = dslContext
                .selectCount()
                .from(jDiseaseTreatment)
                .leftOuterJoin(jDiseaseDetail)
                .on(
                        jDiseaseTreatment.ENTITY_IDENTIFICATION_NUMBER.eq(jDiseaseDetail.ENTITY_IDENTIFICATION_NUMBER),
                        jDiseaseTreatment.ENTITY_MANAGEMENT_NUMBER.eq(jDiseaseDetail.ENTITY_MANAGEMENT_NUMBER),
                        jDiseaseTreatment.DAY.eq(jDiseaseDetail.DAY)

                )
                .leftOuterJoin(jLocalBeefManagement)
                .on(jDiseaseTreatment.ENTITY_IDENTIFICATION_NUMBER.eq(jLocalBeefManagement.ENTITY_IDENTIFICATION_NUMBER))
                .where(this.observationDiaryCondition(predicateObserve))
                .fetchOne(0, Integer.class);


        List<ObservationDiary> observationDiaries = dslContext
                .select(
                        jDiseaseTreatment.asterisk(),
                        jDiseaseDetail.INJECTION_METHOD,
                        jDiseaseDetail.MEDICATION_NAME,
                        jDiseaseDetail.DAY,
                        jDiseaseDetail.NEEDLE_LOSE_YN,
                        jDiseaseDetail.WITHDRAWAL_PERIOD_EXPIRATION_DATE,
                        jLocalBeefManagement.LOCATION
                )
                .from(jDiseaseTreatment)
                .leftOuterJoin(jDiseaseDetail)
                .on(
                        jDiseaseTreatment.ENTITY_IDENTIFICATION_NUMBER.eq(jDiseaseDetail.ENTITY_IDENTIFICATION_NUMBER),
                        jDiseaseTreatment.ENTITY_MANAGEMENT_NUMBER.eq(jDiseaseDetail.ENTITY_MANAGEMENT_NUMBER),
                        jDiseaseTreatment.DAY.eq(jDiseaseDetail.DAY)

                )
                .leftOuterJoin(jLocalBeefManagement)
                .on(jDiseaseTreatment.ENTITY_IDENTIFICATION_NUMBER.eq(jLocalBeefManagement.ENTITY_IDENTIFICATION_NUMBER))
                .where(this.observationDiaryCondition(predicateObserve))
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetchInto(ObservationDiary.class);

        observationDiaries.forEach(observationDiary -> {
            log.trace("observationDiary = {}", observationDiary);
        });


        return new PageImpl<ObservationDiary>(pageable, observationDiaries, total);
    }

    private List<Condition> observationDiaryCondition(ObservationDiary predicate) {

        JDiseaseTreatment jDiseaseTreatment = JDiseaseTreatment.DISEASE_TREATMENT;
        JDiseaseDetail jDiseaseDetail = JDiseaseDetail.DISEASE_DETAIL;
        JLocalBeefManagement jLocalBeefManagement = JLocalBeefManagement.LOCAL_BEEF_MANAGEMENT;

        List<Condition> conditions = new ArrayList<>();
        if (!StringUtils.isEmpty(predicate.getAgeOfMonth())) {
            conditions.add(jLocalBeefManagement.AGE_OF_MONTH.ge(predicate.getAgeOfMonth()));
        }

        return conditions;
    }
}
