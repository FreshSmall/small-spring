package com.demo.framework.core.convert.support;

import com.demo.framework.core.convert.converter.ConverterRegistry;

/**
 * @author: yinchao
 * @ClassName: DefaultConversionService
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/4/22 23:07
 */
public class DefaultConversionService extends GenericConversionService{

    public DefaultConversionService() {
        addDefaultConverters(this);
    }

    public static void addDefaultConverters(ConverterRegistry converterRegistry) {
        // 添加各类类型转换工厂
        converterRegistry.addConverterFactory(new StringToNumberConverterFactory());
    }
}
