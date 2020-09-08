package com.skan.farm.repository.jooq;

import com.skan.farm.domain.model.ObservationDiary;
import com.skan.farm.domain.tables.JDiseaseDetail;
import com.skan.farm.domain.tables.JDiseaseTreatment;
import com.skan.farm.paging.Page;
import com.skan.farm.paging.PageImpl;
import com.skan.farm.paging.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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

        List<ObservationDiary> observationDiaries = dslContext
                .select(
                        jDiseaseTreatment.DISEASE_NAME,
                        jDiseaseTreatment.TREATMENT_DETAILS,
                        jDiseaseTreatment.CURE_AGE_OF_MONTH,
                        jDiseaseTreatment.SYMPTOM,
                        jDiseaseDetail.asterisk()
                )
                .from(jDiseaseTreatment)
                .leftOuterJoin(jDiseaseDetail)
                .on(
                        jDiseaseTreatment.ENTITY_IDENTIFICATION_NUMBER.eq(jDiseaseDetail.ENTITY_IDENTIFICATION_NUMBER),
                        jDiseaseTreatment.ENTITY_MANAGEMENT_NUMBER.eq(jDiseaseDetail.ENTITY_MANAGEMENT_NUMBER),
                        jDiseaseTreatment.DAY.eq(jDiseaseDetail.DAY)

                )
                .where()
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetchInto(ObservationDiary.class);

        observationDiaries.forEach(observationDiary -> {
            log.debug("observationDiary = {}", observationDiary);
        });


        return new PageImpl<ObservationDiary>(pageable, observationDiaries, 0);
    }
}
