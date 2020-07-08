package com.skan.farm.repository.jpa;

import com.skan.farm.code.GenderCode;
import com.skan.farm.model.DiseaseTreatment;
import com.skan.farm.model.LocalBeefManagement;
import com.skan.farm.model.LocalBeefManagementPK;
import com.skan.farm.repository.TestCodeGeneration;
import com.skan.farm.repository.TestNonConfiguration;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

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
class DiseaseTreatmentJpaRepositoryTest implements TestCodeGeneration {

    final LocalBeefManagementJpaRepository localBeefManagementJpaRepository;
    final DiseaseTreatmentJpaRepository diseaseTreatmentJpaRepository;

    @Test
    @Override
    public void saveSelect() {

        String entityNumber = "1111";
        String identityNumber = "2222";

        LocalBeefManagement localBeefManagement = new LocalBeefManagement();
        localBeefManagement.setLocalBeefManagementPK(new LocalBeefManagementPK(entityNumber, identityNumber));
        localBeefManagement.setBirthDay(LocalDate.now());
        localBeefManagement.setGender(GenderCode.FEMALE);

        localBeefManagementJpaRepository.save(localBeefManagement);

        DiseaseTreatment diseaseTreatment1 = new DiseaseTreatment();
        diseaseTreatment1.setDiseaseTreatmentPK(new DiseaseTreatment.DiseaseTreatmentPK(entityNumber, identityNumber, (short) 1));
        diseaseTreatment1.setDiseaseName("감기");

        DiseaseTreatment diseaseTreatment2 = new DiseaseTreatment();
        diseaseTreatment2.setDiseaseTreatmentPK(new DiseaseTreatment.DiseaseTreatmentPK(entityNumber, identityNumber, (short) 1));
        diseaseTreatment2.setDiseaseName("감기");

        diseaseTreatmentJpaRepository.saveAll(Stream.of(diseaseTreatment1, diseaseTreatment2).collect(Collectors.toList()));

    }

    @Override
    public void save() {

    }

    @Test
    @Override
    public void select() {
        diseaseTreatmentJpaRepository.findAll()
                .forEach(diseaseTreatment -> {
                    System.out.println(String.format("diseaseTreatment.getLocalBeefManagement() = %s", diseaseTreatment.getLocalBeefManagement().toString()));
                });


    }

    @Override
    public void delete() {

    }
}