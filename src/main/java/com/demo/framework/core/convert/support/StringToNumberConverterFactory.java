package com.demo.framework.core.convert.support;

import cn.hutool.core.util.NumberUtil;
import com.demo.framework.core.convert.converter.Converter;
import com.demo.framework.core.convert.converter.ConverterFactory;
import com.demo.framework.util.NumberUtils;

/**
 * @author: yinchao
 * @ClassName: StringToNumberConverterFactory
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/4/22 23:15
 */
public class StringToNumberConverterFactory implements ConverterFactory<String, Number> {
    @Override
    public <T extends Number> Converter<String, T> getConverter(Class<T> targetType) {
        return new StringToNumber<>(targetType);
    }

    public final static class StringToNumber<T extends Number> implements Converter<String, T> {

        private final Class<T> targetType;

        public StringToNumber(Class<T> targetType) {
            this.targetType = targetType;
        }

        @Override
        public T convert(String source) {
            if (source.isEmpty()) {
                return null;
            }
            return NumberUtils.parseNumber(source, this.targetType);
        }
    }
}
