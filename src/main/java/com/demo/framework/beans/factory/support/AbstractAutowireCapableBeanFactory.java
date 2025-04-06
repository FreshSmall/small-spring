package com.demo.framework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.demo.framework.beans.BeansException;
import com.demo.framework.beans.PropertyValue;
import com.demo.framework.beans.PropertyValues;
import com.demo.framework.beans.factory.DisposableBean;
import com.demo.framework.beans.factory.InitializingBean;
import com.demo.framework.beans.factory.config.AutowireCapableBeanFactory;
import com.demo.framework.beans.factory.config.BeanDefinition;
import com.demo.framework.beans.factory.config.BeanPostProcessor;
import com.demo.framework.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 抽象的自动装配Bean工厂
 *
 * @author: yinchao
 * @ClassName: AbstractAutowireCapableBeanFactory
 * @Description: 实现了AutowireCapableBeanFactory接口，提供了Bean的创建和属性注入功能
 * @team wuhan operational dev.
 * @date: 2025/4/5 23:45
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibSubClassInstantiationStrategy();

    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object bean;
        try {
            bean = createBeanInstance(beanName, beanDefinition, args);
            // 给 Bean 填充属性
            applyPropertyValues(beanName, bean, beanDefinition);
            // 执行 Bean 的初始化方法和 BeanPostProcessor 的前置和后置处理方法
            bean = initializeBean(beanName, bean, beanDefinition);
        } catch (BeansException e) {
            throw new BeansException("Instantiation of bean failed", e);
        }
        // 注册实现销毁 bean 的钩子
        registerDisposableBeanIfNecessary(beanName, bean, beanDefinition);
        addSingleton(beanName, bean);
        return bean;
    }

    private void registerDisposableBeanIfNecessary(String beanName, Object bean, BeanDefinition beanDefinition) {
        if ("".equals(beanDefinition.getDestroyMethodName()) && bean instanceof DisposableBean) {
            // registerDisposableBean(beanName, new DisposableBeanAdapter(bean, beanName, beanDefinition));
        }
    }

    /**
     * 初始化Bean
     *
     * @param beanName
     * @param bean
     * @param beanDefinition
     * @return
     */
    private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {
        // 1. 执行 BeanPostProcessor Before 处理
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);

        // 2. 待实现初始化方法 invokeInitMethods(beanName, wrappedBean, beanDefinition);
        invokeInitMethods(beanName, wrappedBean, beanDefinition);

        // 3. 执行 BeanPostProcessor After 处理
        wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
        return wrappedBean;
    }

    /**
     * 执行 Bean 的初始化方法
     *
     * @param beanName
     * @param bean
     * @param beanDefinition
     */
    private void invokeInitMethods(String beanName, Object bean, BeanDefinition beanDefinition) throws BeansException {
        // 1. 实现接口 InitializingBean
        if (bean instanceof InitializingBean) {
            ((InitializingBean) bean).afterPropertiesSet();
        }
        // 2. 配置信息 init-method
        String initMethodName = beanDefinition.getInitMethodName();
        if (!"".equals(initMethodName)) {
            try {
                Method initMethod = beanDefinition.getBeanClass().getMethod(initMethodName);
                initMethod.invoke(bean);
            } catch (Exception e) {
                throw new BeansException("Could not find an init method named '" + initMethodName + "' on bean with name '" + beanName + "'");
            }
        }
    }

    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for (PropertyValue propertyValue : propertyValues.getList()) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();
                // 填充引用对象
                if (value instanceof BeanReference) {
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }
                BeanUtil.setFieldValue(bean, name, value);
            }
        } catch (Exception e) {
            throw new BeansException("Error setting property value。beanName:" + beanName);
        }
    }

    protected Object createBeanInstance(String beanName, BeanDefinition beanDefinition, Object[] args) {
        Constructor<?> cons = null;
        Class<?> clazz = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();
        for (Constructor<?> constructor : declaredConstructors) {
            if (args != null && constructor != null && constructor.getParameterTypes().length == args.length) {
                cons = constructor;
                break;
            }
        }
        return instantiationStrategy.instantiate(beanDefinition, beanName, cons, args);
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }

    /**
     * 执行 BeanPostProcessors 的 postProcessBeforeInitialization 方法
     *
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor processor : beanPostProcessors) {
            Object current = processor.postProcessBeforeInitialization(result, beanName);
            if (current == null) {
                return result;
            }
            result = current;
        }
        return result;
    }

    /**
     * 执行 BeanPostProcessors 的 postProcessAfterInitialization 方法
     *
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor processor : beanPostProcessors) {
            Object current = processor.postProcessAfterInitialization(result, beanName);
            if (current == null) {
                return result;
            }
            result = current;
        }
        return result;
    }

    /**
     * 添加 BeanPostProcessor
     *
     * @param beanPostProcessor
     */
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessors.add(beanPostProcessor);
    }
}
