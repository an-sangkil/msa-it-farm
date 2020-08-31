package com.skan.farm.service;

import com.skan.farm.domain.entity.Users;
import com.skan.farm.repository.jpa.UserJpaRepository;
import com.skan.farm.security.AESCryptoUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

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
@Service
@RequiredArgsConstructor
public class AccountService {


    final UserJpaRepository userJpaRepository;

    public Users isUser(Users requestUsers) {
        String userId = requestUsers.getUserId();

        try {
            Users users = userJpaRepository.findByUserId(userId).orElseThrow(NullPointerException::new);
            if (users.getPassword().equals(requestUsers.getPassword())) {
                return users;
            } else {
                log.debug(" {} 님의 비밀번호가 잘못 되었습니다.", userId);
                return null;
            }
        } catch (NullPointerException e) {
            log.debug("{} 의 데이터가 없습니다. ", userId);
            return null;
        }


    }

    public boolean userSave(Users users) throws UnsupportedEncodingException, GeneralSecurityException {

        AESCryptoUtil aesCryptoUtil = new AESCryptoUtil();
        users.setUuid(UUID.randomUUID().toString());
        users.setPassword(aesCryptoUtil.encrypt(users.getPassword()));

        Optional<Users> findUser = userJpaRepository.findByUserId(users.getUserId());
        AtomicBoolean saveIs = new AtomicBoolean(false);

        findUser.ifPresentOrElse(users1 -> saveIs.set(false), () -> {
            userJpaRepository.save(users);
            saveIs.set(true);
        });

        return saveIs.get();
    }
}
