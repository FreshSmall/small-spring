package com.demo.framework.context.support;

import com.demo.framework.beans.BeansException;
import com.demo.framework.beans.factory.config.BeanPostProcessor;
import com.demo.framework.context.ApplicationContext;
import com.demo.framework.context.ApplicationContextAware;

/**
 * @author: yinchao
 * @ClassName: ApplicationContextAwareProcessor
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/4/7 23:25
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {

    private ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ApplicationContextAware) {
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
