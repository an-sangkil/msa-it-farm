package com.skan.farm.utils;

import com.skan.farm.code.GenderCode;
import com.skan.farm.domain.entity.LocalBeefManagement;
import com.skan.farm.domain.entity.LocalBeefManagementPK;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

@Slf4j
class JsonUtilsTest {

    String entityNumber = "1111";
    String identityNumber = "2222";

    @Test
    void jsonToObject () throws Exception {

        LocalBeefManagement localBeefManagement = LocalBeefManagement.builder()
                .localBeefManagementPK(new LocalBeefManagementPK(entityNumber, identityNumber))
                .birthDay(LocalDate.now())
                .gender(GenderCode.FEMALE)
                .build();

        String jsonStr = JsonUtils.convertJson(localBeefManagement);

        log.debug("jsonStr = {}", jsonStr);

        JsonUtils.convertJson(jsonStr,LocalBeefManagement.class);
        jsonStr = "{parentPapaNo:\"\",parentMomNo:\"\",birthDay:\"\",enterDate:\"\",earTagDate:\"\",castrationDate:\"\",gender:\"MALE\",sellYn:\"N\"}";

        LocalBeefManagement localBeefManagement1 = JsonUtils.convertJson(jsonStr,LocalBeefManagement.class);
        log.debug("localBeefManagement1 - {}",localBeefManagement1);

    }

}