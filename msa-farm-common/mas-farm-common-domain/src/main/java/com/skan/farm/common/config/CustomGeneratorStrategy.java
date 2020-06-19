package com.skan.farm.common.config;


import org.jooq.codegen.DefaultGeneratorStrategy;
import org.jooq.meta.Definition;


/**
 * <pre>
 * Description : Jooq code generator 시에 기본 Model 이랑 구분하기 위한 커스텀 클레스
 *
 *
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2020 by CJENM|Mezzomedia. All right reserved.
 * @since 2020-06-18
 */
public class CustomGeneratorStrategy extends DefaultGeneratorStrategy {

    @Override
    public String getJavaClassName(Definition definition, Mode mode) {
        return "J"+ super.getJavaClassName(definition,mode);
    }
}
