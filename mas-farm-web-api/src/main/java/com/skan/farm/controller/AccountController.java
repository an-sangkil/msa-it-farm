package com.skan.farm.controller;

import com.skan.farm.domain.entity.Users;
import com.skan.farm.repository.jpa.UserJpaRepository;
import com.skan.farm.response.Error;
import com.skan.farm.response.Response;
import com.skan.farm.response.Success;
import com.skan.farm.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Optional;

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
@Slf4j
@RestController
@RequiredArgsConstructor
public class AccountController extends AbstractController {

    final UserJpaRepository userJpaRepository;
    final AccountService accountService;

    @PostMapping("/account/sign_up")
    public Response<Users> signup(HttpServletRequest httpServletRequest, @RequestBody @Valid Users users) throws UnsupportedEncodingException, GeneralSecurityException {
        Response<Users> response = new Response<>();
        try {
            accountService.userSave(users);
            response.setDetail(new Success<>(users));
            response.setStatus(Response.ResponseCode.SUCCESS);

        } catch (Exception e) {
            log.debug("find_user error =", e);
            response.setMessage(e.getMessage());
            response.setStatus(Response.ResponseCode.ERROR);
            response.setDetail(new Error<>());
        }

        return response;
    }

    @PostMapping("/account/sign_in")
    public Response<Users> signin(HttpServletRequest httpServletRequest, @RequestBody @Valid Users requestUsers) {

        Response<Users> response = new Response<>();
        try {
            Users isUser = accountService.isUser(requestUsers);

            if (isUser != null) {
                response.setDetail(new Success<>(isUser));
                response.setStatus(Response.ResponseCode.SUCCESS);
            } else {
                response.setMessage("sign in success");
                response.setStatus(Response.ResponseCode.FAILED);
                response.setDetail(new Error<>());
            }
        } catch (Exception e) {
            log.debug("sign in error =", e);
            response.setMessage(e.getMessage());
            response.setStatus(Response.ResponseCode.ERROR);
            response.setDetail(new Error<>());
        }

        return response;
    }

    @GetMapping("/account/find_user")
    public Response<Users> findUser(HttpServletRequest httpServletRequest) {
        Response<Users> response = new Response<>();
        try {
            String userId = httpServletRequest.getParameter("userId");
            Optional<Users> users = userJpaRepository.findByUserId(userId);

            users.ifPresentOrElse(isUser -> {
                response.setDetail(new Success<>(isUser));
                response.setStatus(Response.ResponseCode.SUCCESS);
            }, () -> {
                response.setMessage("sign in success");
                response.setStatus(Response.ResponseCode.FAILED);
                response.setDetail(new Error<>());
            });

        } catch (Exception e) {
            log.debug("find_user error =", e);
            response.setMessage(e.getMessage());
            response.setStatus(Response.ResponseCode.ERROR);
            response.setDetail(new Error<>());
        }

        return response;
    }
}
