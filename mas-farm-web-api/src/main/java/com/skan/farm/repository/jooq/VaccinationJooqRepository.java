package com.skan.farm.repository.jooq;

import com.skan.farm.domain.entity.Vaccination;
import com.skan.farm.domain.tables.JVaccination;
import com.skan.farm.paging.PageImpl;
import com.skan.farm.paging.Pageable;
import lombok.RequiredArgsConstructor;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    public PageImpl<Vaccination> findByPaging(Vaccination predicateVaccination, Pageable pageable) {


        JVaccination jVaccination = JVaccination.VACCINATION;

        List<Condition> conditions = this.dynamicConditionVaccination(predicateVaccination);

        int total = dslContext.selectCount()
                .from(jVaccination)
                .where(conditions)
                .fetchOne(0, Integer.class);

        List<Vaccination> vaccinations = dslContext.select(jVaccination.asterisk())
                .from(jVaccination)
                .where(conditions)
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetchInto(Vaccination.class);


        return new PageImpl<Vaccination>(pageable, vaccinations, (int) total);
    }

    public List<Condition> dynamicConditionVaccination(Vaccination predicate) {
        List<Condition> conditions = new ArrayList<>();
        JVaccination jVaccination = JVaccination.VACCINATION;

        String stringPattern = "%{0}%";
        if (predicate.getVaccinationPK().getSeq() != null) {
            conditions.add(jVaccination.SEQ.eq(predicate.getVaccinationPK().getSeq()));
        }
        if (predicate.getVaccinationPK().getDay() != null) {
            //conditions.add(jVaccination.DAY.between());
        }
        if (!StringUtils.isEmpty(predicate.getVaccinationPK().getEntityIdentificationNumber())) {
            conditions.add(jVaccination.ENTITY_IDENTIFICATION_NUMBER.like(MessageFormat.format(stringPattern,predicate.getVaccinationPK().getEntityIdentificationNumber())));
        }

        if (!StringUtils.isEmpty(predicate.getLocation())) {
            conditions.add(jVaccination.LOCATION.like(MessageFormat.format(stringPattern, predicate.getLocation())));
        }

        return conditions;
    }
}
