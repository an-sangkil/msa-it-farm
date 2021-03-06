package com.skan.farm.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <pre>
 * Description : 성공한 데이터에 대한 상세
 *
 *
 * </pre>
 *
 * @author ${user}
 * @version Copyright (C) 2018 by Mezzomedia. All right reserved.
 * @since 2018-11-07
 */
@Getter
@Setter
@ToString(callSuper = true)
public class Success<T> extends AbstractResponseData {

    private T contents;

    public Success() {
        // 성공시 기본 CODE 1000
        super.code = 1000;
    }

    public Success(T t) {
        super();
        // 성공시 기본 CODE 1000
        super.code = 1000;
        this.contents = t;
    }

    public Success(int code, T t) {
        super();
        super.code = code;
        this.contents = t;
    }

}
