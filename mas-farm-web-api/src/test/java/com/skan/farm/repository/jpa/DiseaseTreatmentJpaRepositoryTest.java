package com.skan.farm.repository.jpa;

import com.skan.farm.code.GenderCode;
import com.skan.farm.domain.entity.DiseaseDetail;
import com.skan.farm.domain.entity.DiseaseTreatment;
import com.skan.farm.domain.entity.LocalBeefManagement;
import com.skan.farm.domain.entity.LocalBeefManagementPK;
import com.skan.farm.repository.TestCodeGeneration;
import com.skan.farm.repository.TestNonConfiguration;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
    final LocalDate toDay  = LocalDate.now();

    String entityNumber = "1111";
    String identityNumber = "2222";

    @Test
    public void saveAndSaveAll() {




        LocalBeefManagement localBeefManagement = new LocalBeefManagement();
        localBeefManagement.setLocalBeefManagementPK(new LocalBeefManagementPK(entityNumber, identityNumber));
        localBeefManagement.setBirthDay(LocalDate.now());
        localBeefManagement.setGender(GenderCode.FEMALE);
        localBeefManagementJpaRepository.save(localBeefManagement);


        DiseaseTreatment diseaseTreatment1 = new DiseaseTreatment();
        diseaseTreatment1.setDiseaseTreatmentPK(new DiseaseTreatment.DiseaseTreatmentPK(entityNumber, identityNumber, toDay));
        diseaseTreatment1.setDiseaseName("감기");

        DiseaseTreatment diseaseTreatment2 = new DiseaseTreatment();
        diseaseTreatment2.setDiseaseTreatmentPK(new DiseaseTreatment.DiseaseTreatmentPK(entityNumber, identityNumber, toDay));
        diseaseTreatment2.setDiseaseName("감기");

        diseaseTreatmentJpaRepository.saveAll(Stream.of(diseaseTreatment1, diseaseTreatment2).collect(Collectors.toList()));

    }

    @Override
    public void saveSelect() {

    }

    @Test
    @Override
    public void save() {

        LocalBeefManagement localBeefManagement = new LocalBeefManagement();
        localBeefManagement.setLocalBeefManagementPK(new LocalBeefManagementPK(entityNumber, identityNumber));
        localBeefManagement.setBirthDay(LocalDate.now());
        localBeefManagement.setGender(GenderCode.FEMALE);

        localBeefManagementJpaRepository.save(localBeefManagement);

        List<DiseaseTreatment> diseaseTreatments = new ArrayList<>();
        IntStream.rangeClosed(1,3).forEach(value -> {
            DiseaseTreatment diseaseTreatment1 = new DiseaseTreatment();
            diseaseTreatment1.setDiseaseTreatmentPK(new DiseaseTreatment.DiseaseTreatmentPK(entityNumber, identityNumber, toDay));
            diseaseTreatment1.setDiseaseName("감기"+ value);

            diseaseTreatments.add(diseaseTreatment1);
        });

        diseaseTreatmentJpaRepository.saveAll(diseaseTreatments);
    }

    @Test
    @Override
    public void select() {
        diseaseTreatmentJpaRepository.findAll()
                .forEach(diseaseTreatment -> {
                    System.out.println(String.format("diseaseTreatment.getLocalBeefManagement() = %s", diseaseTreatment.getLocalBeefManagement().toString()));
                });


    }

    @Test
    public void findByDiseaseTreatmentPK_EntityManagementNumberAndDiseaseTreatmentPK_EntityIdentificationNumber() {
        String entityNumber = "1111";
        String identityNumber = "2222";
        diseaseTreatmentJpaRepository.findByDiseaseTreatmentPK_EntityManagementNumberAndDiseaseTreatmentPK_EntityIdentificationNumber(entityNumber, identityNumber)
                .forEach(diseaseTreatment -> {
                    System.out.println(String.format("diseaseTreatment.getLocalBeefManagement() = %s", diseaseTreatment.getLocalBeefManagement().toString()));
                });


    }

    @Override
    public void delete() {

    }

    @Test
    void selectDiseaseDetail () {

        diseaseTreatmentJpaRepository.findAll()
                .forEach(diseaseTreatment -> {
                    System.out.println(String.format("diseaseTreatment.getLocalBeefManagement() = %s", diseaseTreatment.getLocalBeefManagement().toString()));

                    System.out.println(diseaseTreatment.getDiseaseDetails());
                });

    }
}