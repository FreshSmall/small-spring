package com.demo.framework.core.convert.converter;

/**
 * @author: yinchao
 * @ClassName: ConverterFactory
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/4/22 23:06
 */
public interface ConverterFactory<S,R> {


    /**
     * Get the converter to convert from S to target type T, where T is also an instance of R.
     * @param <T> the target type
     * @param targetType the target type to convert to
     * @return a converter from S to T
     */
    <T extends R> Converter<S, T> getConverter(Class<T> targetType);
}
