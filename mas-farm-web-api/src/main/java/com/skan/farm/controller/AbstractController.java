package com.skan.farm.controller;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * <pre>
 * Description :
 *
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2020 by CJENM|Mezzomedia. All right reserved.
 * @since 2020-07-22
 */
@Slf4j
public abstract class AbstractController {

    /*
        Tomcat에서  getReader, getInpustream 을 못읽도록 막아두어 톰겟에서는 사용불가
     */
    @Deprecated
    public String getBody(HttpServletRequest request) throws IOException {

        String body = null;
        StringBuilder stringBuilder = new StringBuilder();

        try (InputStream inputStream = request.getInputStream();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        ) {

            if (inputStream != null && bufferedReader != null) {
                String tempStr = "";
                while ((tempStr = bufferedReader.readLine()) != null) {
                    stringBuilder.append(tempStr);
                }
            }

        }

        body = stringBuilder.toString();
        log.debug("body data = {}", body);
        return body;
    }
}
