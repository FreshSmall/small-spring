package com.demo.framework.context.support;

import com.demo.framework.beans.BeansException;
import com.demo.framework.beans.factory.FactoryBean;
import com.demo.framework.beans.factory.InitializingBean;
import com.demo.framework.core.convert.ConversionService;
import com.demo.framework.core.convert.converter.Converter;
import com.demo.framework.core.convert.converter.ConverterFactory;
import com.demo.framework.core.convert.converter.ConverterRegistry;
import com.demo.framework.core.convert.converter.GenericConverter;
import com.demo.framework.core.convert.support.DefaultConversionService;
import com.demo.framework.core.convert.support.GenericConversionService;
import com.sun.istack.internal.Nullable;

import java.beans.Beans;
import java.util.Set;

/**
 * @author: yinchao
 * @ClassName: ConversionServiceFactoryBean
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/4/22 23:21
 */
public class ConversionServiceFactoryBean implements FactoryBean<ConversionService>, InitializingBean {

    @Nullable
    private Set<?> converters;

    @Nullable
    private GenericConversionService conversionService;

    @Override
    public ConversionService getObject() throws Exception {
        return conversionService;
    }

    @Override
    public Class<?> getObjectType() {
        return conversionService.getClass();
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws BeansException {
        this.conversionService = new DefaultConversionService();
        registerConverters(converters, conversionService);
    }

    private void registerConverters(Set<?> converters, ConverterRegistry registry) {
        if (converters != null) {
            for (Object converter : converters) {
                if (converter instanceof GenericConverter) {
                    registry.addConverter((GenericConverter) converter);
                } else if (converter instanceof Converter<?, ?>) {
                    registry.addConverter((Converter<?, ?>) converter);
                } else if (converter instanceof ConverterFactory<?, ?>) {
                    registry.addConverterFactory((ConverterFactory<?, ?>) converter);
                } else {
                    throw new IllegalArgumentException("Each converter object must implement one of the " +
                            "Converter, ConverterFactory, or GenericConverter interfaces");
                }
            }
        }
    }

    public void setConverters(Set<?> converters) {
        this.converters = converters;
    }

}
