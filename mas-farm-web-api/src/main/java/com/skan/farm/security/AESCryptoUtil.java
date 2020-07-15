package com.skan.farm.security;


import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;


/**
 * <pre>
 * Description : AES 256 암/복 호화 모듈
 *                 - ASE 256 사용하기 위해 KeyByte 32 사용,  128 사용시 16
 *
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2019 by CJENM|MezzoMedia. All right reserved.
 * @since 2019-01-15
 */
public class AESCryptoUtil {

  private final String iv;
  private final Key keySpec;
  private final static String algorithm = "AES/CBC/PKCS5Padding";
  
  // 키 길이 32 byte 사용 AES256 bit
  private final String KEY = "skan12skan!@skan23skan@#skan56sk";

  /**
   *
   * @throws UnsupportedEncodingException
   */
  public AESCryptoUtil() throws UnsupportedEncodingException {
    // IV 값
    this.iv = KEY.substring(0, 16);

    // ASE 256 사용하기 위해 KeyByte 32 사용,  128 사용시 16
    // 32 보다 큰경우 시작점으로 부터 32까지 재복사  (사용자 실수 예방 )
    byte[] keyBytes = new byte[32];
    byte[] b = KEY.getBytes(StandardCharsets.UTF_8);
    int len = b.length;
    if (len > keyBytes.length) {
      len = keyBytes.length;
    }

    System.arraycopy(b, 0, keyBytes, 0, len);
    this.keySpec = new SecretKeySpec(keyBytes, "AES");

  }

  /**
   *
   * @param key : 키 길이가 32 byte 면 AES256 bit , 16 byte 면 AES128 bit
   * @throws UnsupportedEncodingException
   */
  public AESCryptoUtil(String key) throws UnsupportedEncodingException {

    // IV 값
    this.iv = KEY.substring(0, 16);


    // 32 보다 큰경우 시작점으로 부터 32까지만 복사한다.  (사용자 실수 예방하기 위함)
    byte[] keyBytes = new byte[32];
    byte[] b = key.getBytes(StandardCharsets.UTF_8);
    int len = b.length;
    if (len > keyBytes.length) {
      len = keyBytes.length;
    }

    System.arraycopy(b, 0, keyBytes, 0, len);
    this.keySpec = new SecretKeySpec(keyBytes, "AES");

  }

  /**
   * 암호화
   * @param str
   * @return
   * @throws NoSuchAlgorithmException
   * @throws GeneralSecurityException
   * @throws UnsupportedEncodingException
   */
  public String encrypt(String str) throws NoSuchAlgorithmException, GeneralSecurityException, UnsupportedEncodingException {
    Cipher c = Cipher.getInstance(algorithm);
    c.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));
    byte[] encrypted = c.doFinal(str.getBytes(StandardCharsets.UTF_8));
    return new String(Base64Utils.encode(encrypted));
  }

  /**
   * 복화화
   * @param str
   * @return
   * @throws NoSuchAlgorithmException
   * @throws GeneralSecurityException
   * @throws UnsupportedEncodingException
   */
  public String decrypt(String str) throws NoSuchAlgorithmException, GeneralSecurityException, UnsupportedEncodingException {
    Cipher c = Cipher.getInstance(algorithm);
    c.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));
    byte[] byteStr = Base64Utils.decode(str.getBytes());
    return new String(c.doFinal(byteStr), StandardCharsets.UTF_8);
  }



}

