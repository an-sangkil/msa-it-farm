package com.skan.farm.service;

import com.skan.farm.domain.entity.CalvesManagement;
import com.skan.farm.domain.entity.DiseaseTreatment;
import com.skan.farm.domain.entity.LocalBeefManagement;
import com.skan.farm.domain.entity.LocalBeefManagementPK;
import com.skan.farm.paging.PageImpl;
import com.skan.farm.repository.jooq.LocalBeefManagementJooqRepository;
import com.skan.farm.repository.jpa.LocalBeefManagementJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <pre>
 * Description :
 *
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2020 by CJENM|Mezzomedia. All right reserved.
 * @since 2020-07-09
 */
@Service
@RequiredArgsConstructor
public class CattleManagementService {

    private final LocalBeefManagementJpaRepository localBeefManagementJpaRepository;
    private final LocalBeefManagementJooqRepository localBeefManagementJooqRepository;

    public org.springframework.data.domain.Page findAllPaging(Pageable pageable) {
        return localBeefManagementJpaRepository.findAll(pageable);
    }

    /**
     * 페이징 검색
     * @param predicate
     * @param pageable
     * @return
     */
    public PageImpl<LocalBeefManagement> findAll(LocalBeefManagement predicate, com.skan.farm.paging.Pageable pageable) {
        return  localBeefManagementJooqRepository.findAll(predicate, pageable);
    }

    /**
     * 한객체만 검색
     * @param entityId
     * @param identityId
     * @return
     */
    public LocalBeefManagement findOne(String entityId, String identityId) {

        Optional<LocalBeefManagement> localBeefManagementOptional = this.localBeefManagementJpaRepository.findById(new LocalBeefManagementPK(identityId,entityId));
        LocalBeefManagement localBeefManagement = localBeefManagementOptional.orElseThrow(NullPointerException::new);

        // 역순 정렬
        Comparator<CalvesManagement> calvesManagementComparator = Comparator.comparing(CalvesManagement::getCreatedTime).reversed();

        if (localBeefManagement.getCalvesManagementSet() != null) {
            localBeefManagement.setCalvesManagementSet(localBeefManagement.getCalvesManagementSet()
                    .stream()
                    .sorted(calvesManagementComparator).collect(Collectors.toCollection(LinkedHashSet::new)));
        }

        if (localBeefManagement.getDiseaseTreatmentSet() != null) {
            localBeefManagement.setDiseaseTreatmentSet(localBeefManagement
                    .getDiseaseTreatmentSet()
                    .stream()
                    .sorted(Comparator.comparing(DiseaseTreatment::getCreatedTime).reversed()).collect(Collectors.toCollection(LinkedHashSet::new)));
        }

        return localBeefManagement;

    }

}
