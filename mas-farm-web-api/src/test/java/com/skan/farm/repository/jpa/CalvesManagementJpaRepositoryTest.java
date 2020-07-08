package com.skan.farm.repository.jpa;

import com.skan.farm.code.GenderCode;
import com.skan.farm.model.CalvesManagement;
import com.skan.farm.model.LocalBeefManagement;
import com.skan.farm.model.LocalBeefManagementPK;
import com.skan.farm.repository.TestCodeGeneration;
import com.skan.farm.repository.TestNonConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.List;

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
@Slf4j
class CalvesManagementJpaRepositoryTest implements TestCodeGeneration {

    final LocalBeefManagementJpaRepository localBeefManagementJpaRepository;
    final CalvesManagementJpaRepository calvesManagementJpaRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("저장 후 조회 ")
    @Override
    public void saveSelect() {

        String entityNumber = "1111";
        String identityNumber = "2222";

        LocalBeefManagement localBeefManagement = new LocalBeefManagement();
        localBeefManagement.setLocalBeefManagementPK(new LocalBeefManagementPK(entityNumber, identityNumber));
        localBeefManagement.setBirthDay(LocalDate.now());
        localBeefManagement.setGender(GenderCode.FEMALE);

        localBeefManagementJpaRepository.save(localBeefManagement);

        CalvesManagement calvesManagement = CalvesManagement.builder()
                .calvesManagementPK(new CalvesManagement.CalvesManagementPK(entityNumber, identityNumber, (short) 1))
                .years(String.valueOf(LocalDate.now().getYear()))
                .build();
        ;
        CalvesManagement calvesManagement2 = CalvesManagement.builder()
                .calvesManagementPK(new CalvesManagement.CalvesManagementPK(entityNumber, identityNumber, (short) 2))
                .years(String.valueOf(LocalDate.now().getYear()))
                .build();
        ;

        calvesManagementJpaRepository.saveAll(List.of(calvesManagement, calvesManagement2));

        List<CalvesManagement> calvesManagements = calvesManagementJpaRepository.findAll();
        calvesManagements.forEach(calvesManagement1 -> {
            log.debug("calvesManagement1.getLocalBeefManagement() : {}" , calvesManagement1.getLocalBeefManagement());
        });


    }

    @Test
    @Override
    @DisplayName("자식 저장 하며 부모 같이 저장 - 영속성 전파")
    public void save() {
        String entityNumber = "1111";
        String identityNumber = "2222";

        LocalBeefManagement localBeefManagement = new LocalBeefManagement();
        localBeefManagement.setLocalBeefManagementPK(new LocalBeefManagementPK(entityNumber, identityNumber));
        localBeefManagement.setBirthDay(LocalDate.now());
        localBeefManagement.setGender(GenderCode.FEMALE);

        CalvesManagement calvesManagement = CalvesManagement.builder()
                .calvesManagementPK(new CalvesManagement.CalvesManagementPK(entityNumber, identityNumber, (short) 1))
                .years(String.valueOf(LocalDate.now().getYear()))
                .build();
        ;

        calvesManagement.setLocalBeefManagement(localBeefManagement);


        calvesManagementJpaRepository.save(calvesManagement);

    }

    @Override
    public void select() {

    }

    @Override
    public void delete() {

    }
}