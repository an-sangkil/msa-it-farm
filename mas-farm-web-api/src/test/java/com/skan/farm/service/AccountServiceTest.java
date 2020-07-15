package com.skan.farm.service;

import com.skan.farm.model.Users;
import com.skan.farm.repository.TestNonConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

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
 * @since 2020-07-15
 */
@Slf4j
@TestNonConfiguration
@RequiredArgsConstructor
class AccountServiceTest {

    @Autowired
    AccountService accountService;

    @Test
    void isUser() {
    }

    @Test
    void userSave() throws UnsupportedEncodingException, GeneralSecurityException {

        Users user = Users.builder()
                .userId("skan3")
                .password("password1!")
                .userName("이름1")
                .email("sangkil.an@kakao.com")
                .build();

        boolean saveIs = accountService.userSave(user);
        System.out.println("saveIs = " + saveIs);

    }
}