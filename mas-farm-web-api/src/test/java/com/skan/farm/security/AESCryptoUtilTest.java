package com.skan.farm.security;

import org.junit.jupiter.api.Test;

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
class AESCryptoUtilTest {
    AESCryptoUtil aesCryptoUtil = new AESCryptoUtil();

    AESCryptoUtilTest() throws UnsupportedEncodingException {
    }

    @Test
    void encrypt() throws GeneralSecurityException, UnsupportedEncodingException {
        String planText = "aaaaBBBB";
        String result = aesCryptoUtil.encrypt(planText);
        System.out.println(result);
        result = aesCryptoUtil.encrypt(planText);
        System.out.println(result);
        result = aesCryptoUtil.encrypt(planText);
        System.out.println(result);

        result = aesCryptoUtil.decrypt(result);
        System.out.println(result);
    }

    @Test
    void decrypt() {
    }
}