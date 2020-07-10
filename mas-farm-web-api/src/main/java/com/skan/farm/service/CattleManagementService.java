package com.skan.farm.service;

import com.skan.farm.model.LocalBeefManagement;
import com.skan.farm.paging.PageableDefault;
import com.skan.farm.paging.PageableJooq;
import com.skan.farm.repository.jooq.LocalBeefManagementJooqRepository;
import com.skan.farm.repository.jpa.LocalBeefManagementJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    public Page<LocalBeefManagement> findAllPaging(Pageable pageable) {
        return localBeefManagementJpaRepository.findAll(pageable);
    }
    public com.skan.farm.paging.Page<LocalBeefManagement> findAll(LocalBeefManagement localBeefManagement, PageableDefault pageableDefault) {
        return localBeefManagementJooqRepository.findAll(localBeefManagement, pageableDefault);
    }

}
