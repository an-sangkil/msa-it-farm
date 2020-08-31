package com.skan.farm.repository.jpa;

import com.skan.farm.code.GenderCode;
import com.skan.farm.domain.entity.CattleSellStoreInformation;
import com.skan.farm.domain.entity.LocalBeefManagement;
import com.skan.farm.domain.entity.LocalBeefManagementPK;
import com.skan.farm.repository.TestCodeGeneration;
import com.skan.farm.repository.TestNonConfiguration;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.time.LocalDate;

/**
 * <pre>
 * Description :
 *
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2020 by CJENM|Mezzomedia. All right reserved.
 * @since 2020-07-08
 */
@TestNonConfiguration
@RequiredArgsConstructor
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CattleSellStoreInformationJpaRepositoryTest implements TestCodeGeneration {

    final LocalBeefManagementJpaRepository localBeefManagementJpaRepository;
    final CattleSellStoreInformationJpaRepository cattleSellStoreInformationJpaRepository;

    @Test
    @Override
    public void saveSelect() {
        this.save();
        this.select();
    }

    @Override
    public void save() {
        String entityNumber = "1111";
        String identityNumber = "2222";

        LocalBeefManagement localBeefManagement = new LocalBeefManagement();
        localBeefManagement.setLocalBeefManagementPK(new LocalBeefManagementPK(entityNumber, identityNumber));
        localBeefManagement.setBirthDay(LocalDate.now());
        localBeefManagement.setGender(GenderCode.FEMALE);

        localBeefManagementJpaRepository.save(localBeefManagement);

        CattleSellStoreInformation cattleSellStoreInformation = CattleSellStoreInformation.builder()
                .localBeefManagementPK(new LocalBeefManagementPK(entityNumber, identityNumber))
                .sellDate(LocalDate.now())
                .sellNote("노트")
                .build();

        cattleSellStoreInformationJpaRepository.save(cattleSellStoreInformation);

    }

    @Override
    public void select() {
        System.out.println("---------------------------------- 절취선 -------------------------------------------");
        cattleSellStoreInformationJpaRepository.findAll().forEach(cattleSellStoreInformation -> {
            System.out.println(String.format("cattleSellStoreInformation.getLocalBeefManagement() : %s", cattleSellStoreInformation.getLocalBeefManagement()));
        });

    }

    @Override
    public void delete() {

    }
}