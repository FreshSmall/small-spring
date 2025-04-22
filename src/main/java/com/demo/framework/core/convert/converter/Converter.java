package com.demo.framework.core.convert.converter;

/**
 * @author: yinchao
 * @ClassName: Converter
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/4/22 23:05
 */
public interface Converter<S,T> {

    T convert(S source);
}
