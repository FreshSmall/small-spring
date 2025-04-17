package com.demo.framework.beans.factory.config;

import com.demo.framework.beans.factory.HierarchicalBeanFactory;
import com.demo.framework.util.StringValueResolver;

/**
 * 可配置的Bean工厂接口
 *
 * @author: yinchao
 * @ClassName: ConfigurableBeanFactory
 * @Description: 提供配置Bean工厂的能力
 * @team wuhan operational dev.
 * @date: 2025/4/5 23:32
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    /**
     * 单例作用域标识
     */
    String SCOPE_SINGLETON = "singleton";

    /**
     * 原型作用域标识
     */
    String SCOPE_PROTOTYPE = "prototype";

    /**
     * 添加 BeanPostProcessor
     *
     * @param beanPostProcessor
     */
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);


    /**
     * Add a String resolver for embedded values such as annotation attributes.
     * @param valueResolver the String resolver to apply to embedded values
     * @since 3.0
     */
    void addEmbeddedValueResolver(StringValueResolver valueResolver);

    /**
     * Resolve the given embedded value, e.g. an annotation attribute.
     * @param value the value to resolve
     * @return the resolved value (may be the original value as-is)
     * @since 3.0
     */
    String resolveEmbeddedValue(String value);
}
