package com.skan.farm.service;

import com.skan.farm.model.LocalBeefManagement;
import com.skan.farm.repository.jpa.LocalBeefManagementJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<LocalBeefManagement> findAllPaging(LocalBeefManagement localBeefManagement, Pageable pageable, Sort sort) {
        return  localBeefManagementJpaRepository.findAll();
    }

}
