package com.skan.farm.service;

import com.skan.farm.domain.entity.CalvesManagement;
import com.skan.farm.repository.jooq.CalvesManagementJooqRepository;
import com.skan.farm.repository.jpa.CalvesManagementJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

/**
 * <pre>
 * Description :
 *  분만관리 서비스
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2020 by CJENM|Mezzomedia. All right reserved.
 * @since 2020-07-14
 */
@Service
@RequiredArgsConstructor
public class ChildBirthManagementService {

    final CalvesManagementJooqRepository calvesManagementJooqRepository;
    final CalvesManagementJpaRepository calvesManagementJpaRepository;
    /**
     * 예정일 계산
     *
     * @param fertilizationDate
     * @return
     * @Reference @link{http://www.top-cow.com/bbs/board.php?bo_table=data1&wr_id=16&page=7}
     */
    protected LocalDateTime expectedDateConfinementCalculate(LocalDateTime fertilizationDate) {

        if (fertilizationDate.getMonth().getValue() <= 3) {
            fertilizationDate = fertilizationDate.plusMonths(12).minusMonths(3);
        } else {
            fertilizationDate = fertilizationDate.minusMonths(3);
            fertilizationDate = fertilizationDate.plusYears(1);
        }

        if (fertilizationDate.getDayOfMonth() >= 21) {
            fertilizationDate = fertilizationDate.plusDays(10).minusDays(30);
            fertilizationDate = fertilizationDate.plusMonths(1);
        } else {
            fertilizationDate = fertilizationDate.plusDays(10);
        }

        return fertilizationDate;
    }

    /**
     * 분만관리 저장 
     *  - 회차에 대한 로직 정의됨
     * @param calvesManagement
     * @param entityId
     * @param identityId
     */
    public void save(CalvesManagement calvesManagement, String entityId, String identityId) {
        //ApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.skan.farm");
        //CalvesManagementJpaRepository calvesManagementJpaRepository = applicationContext.getBean(CalvesManagementJpaRepository.class);
        //CalvesManagementJooqRepository calvesManagementJooqRepository = applicationContext.getBean(CalvesManagementJooqRepository.class);

        if (StringUtils.isEmpty(calvesManagement.getCalvesManagementPK().getSeq())) {
            var lastCount = calvesManagementJooqRepository.findByLastCount(entityId, identityId);
            var fertilizationIndex = calvesManagementJooqRepository.findByFertilizationIndex(entityId, identityId);

            calvesManagement.setFertilizationIndex((short) (fertilizationIndex+1));
            calvesManagement.setCalvesManagementPK(new CalvesManagement.CalvesManagementPK(entityId, identityId, (short) (lastCount + 1)));
        }

        calvesManagement.setExpectedDateConfinement(this.expectedDateConfinementCalculate(calvesManagement.getFertilizationDate()));
        calvesManagementJpaRepository.save(calvesManagement);

    }
}
