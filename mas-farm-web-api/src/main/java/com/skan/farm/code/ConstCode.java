package com.skan.farm.code;

/**
 * <pre>
 * Description :
 *
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2020 by CJENM|Mezzomedia. All right reserved.
 * @since 2020-08-14
 */
public enum ConstCode {
    DEGREES_BELOW_ZERO("영하"),DEGREES_ABOVE_ZERO("영상"),
    MINUS_DEGREES("영하"),PLUS_DEGREES("영상"),
    ;

    public String codeName;

    ConstCode(String s) {
        this.codeName = s;
    }
}
