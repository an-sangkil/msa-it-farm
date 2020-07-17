package com.skan.farm.response;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * Description : 응답 요청에 대한 공통 객체
 *
 *  @example :
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2018 by Mezzomedia. All right reserved.
 * @since 2018-11-07
 */
@Getter
@Setter
@Slf4j
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {


    /**
     * 응답 결과에 대한  코드
     */
    private int code;

    /**
     * 상태
     */
    private ResponseCode status;

    /**
     * 호출한 url 정보
     */
    private String url;

    /**
     * 결과 메세지
     */
    private String message;

    /**
     * 결과 데이터 상세
     * {@link Success<T> 성공 데이터 }.
     * {@link Error<T> 성공 데이터 }.
     */
    private AbstractResponseData<T> detail;

    public Response(int code, ResponseCode status, AbstractResponseData<T> detail) {
        this.code = code;
        this.status = status;
        this.detail = detail;
    }

    /**
     * 응답 코드
     */
    public enum ResponseCode {
        SUCCESS(1000), ERROR(2000) ;
        private int code;
        ResponseCode(int i) {
            code = i;
        }
        public int getCode() {
            return code;
        }
    }

}
