package com.skan.farm.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <pre>
 * Description : 에러 메세지에 대한 상세 코드
 *
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2018 by skan. All right reserved.
 * @since 2018-11-07
 */
@Getter
@Setter
@ToString(callSuper = true)
public class Error<T> extends AbstractResponseData {

    private T contents;

    public Error() {
        super.code = 2000;
    }


    public Error(T t) {
        super();

        // 실패시 기본 CODE 2000
        super.code = 2000;
        this.contents = t;
    }

    public Error(int code, T t) {
        super();
        super.code = code;
        this.contents = t;
    }


}
