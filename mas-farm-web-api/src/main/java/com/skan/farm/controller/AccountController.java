package com.skan.farm.controller;

import com.skan.farm.model.Users;
import com.skan.farm.repository.jpa.UserJpaRepository;
import com.skan.farm.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Optional;
import java.util.UUID;

/**
 * <pre>
 * Description :
 *
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2020 by CJENM|Mezzomedia. All right reserved.
 * @since 2020-07-14
 */
@RestController
@RequiredArgsConstructor
public class AccountController {

    final UserJpaRepository userJpaRepository;
    final AccountService accountService;

    @PostMapping("/account/sign_up")
    public void signup(HttpServletRequest httpServletRequest, @Valid Users users) throws UnsupportedEncodingException, GeneralSecurityException {

        accountService.userSave(users);

    }

    @PostMapping("/account/sign_in")
    public void signin(HttpServletRequest httpServletRequest, @Valid Users requestUsers) {
        boolean isUser = accountService.isUser(requestUsers);
    }
    @PostMapping("/account/find_user")
    public void findUser(HttpServletRequest httpServletRequest){
        String userId = httpServletRequest.getParameter("userId");
        Optional<Users> users = userJpaRepository.findByUserId(userId);
        
    }
}
