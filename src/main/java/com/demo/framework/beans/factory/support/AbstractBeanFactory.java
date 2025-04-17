package com.demo.framework.beans.factory.support;

import cn.hutool.core.util.ClassUtil;
import com.demo.framework.beans.BeansException;
import com.demo.framework.beans.factory.BeanFactory;
import com.demo.framework.beans.factory.FactoryBean;
import com.demo.framework.beans.factory.config.BeanDefinition;
import com.demo.framework.beans.factory.config.BeanPostProcessor;
import com.demo.framework.beans.factory.config.ConfigurableBeanFactory;
import com.demo.framework.util.StringValueResolver;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象的Bean工厂
 *
 * @author: yinchao
 * @ClassName: AbstractBeanFactory
 * @Description: 实现BeanFactory接口，提供获取Bean对象的方法
 * @team wuhan operational dev.
 * @date: 2025/4/6 00:15
 */
public abstract class AbstractBeanFactory extends FactoryBeanRegistrySupport implements ConfigurableBeanFactory {

    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<BeanPostProcessor>();

    private final List<StringValueResolver> embeddedValueResolvers = new ArrayList<>();


    @Override
    public Object getBean(String name) throws BeansException {
        return doGetBean(name, null);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return doGetBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return (T) getBean(name);
    }

    /**
     * 获取Bean对象
     *
     * @param name
     * @param args
     * @return
     */
    protected <T> T doGetBean(String name, final Object... args) {
        Object  shareInstance = getSingleton(name);
        if (shareInstance != null) {
            return (T)getObjectForBeanInstance(shareInstance,name);
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        Object bean = createBean(name, beanDefinition, args);
        return (T) getObjectForBeanInstance(bean, name);
    }

    private Object getObjectForBeanInstance(Object bean, String name) {
        if (!(bean instanceof FactoryBean)) {
            return bean;
        }
        Object object = getCachedObjectForFactoryBean(name);
        if (object == null) {
            object = getObjectFromFactoryBean((FactoryBean) bean, name);
        }
        return object;
    }

    /**
     * 添加 BeanPostProcessor
     *
     * @param beanPostProcessor
     */
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    public List<BeanPostProcessor> getBeanPostProcessors() {
        return beanPostProcessors;
    }

    @Override
    public void addEmbeddedValueResolver(StringValueResolver valueResolver) {
        this.embeddedValueResolvers.add(valueResolver);
    }

    @Override
    public String resolveEmbeddedValue(String value) {
        String result = value;
        for (StringValueResolver resolver : this.embeddedValueResolvers) {
            result = resolver.resolveStringValue(result);
        }
        return result;
    }

    public ClassLoader getBeanClassLoader() {
        return ClassUtil.getClassLoader();
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object... args) throws BeansException;
}
