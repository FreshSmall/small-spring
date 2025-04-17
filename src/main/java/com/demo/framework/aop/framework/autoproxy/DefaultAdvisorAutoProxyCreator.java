package com.demo.framework.aop.framework.autoproxy;

import com.demo.framework.aop.AdvisedSupport;
import com.demo.framework.aop.Advisor;
import com.demo.framework.aop.ClassFilter;
import com.demo.framework.aop.PointCut;
import com.demo.framework.aop.TargetSource;
import com.demo.framework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import com.demo.framework.aop.framework.ProxyFactory;
import com.demo.framework.beans.BeansException;
import com.demo.framework.beans.PropertyValues;
import com.demo.framework.beans.factory.BeanFactory;
import com.demo.framework.beans.factory.BeanFactoryAware;
import com.demo.framework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.demo.framework.beans.factory.support.DefaultListableBeanFactory;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

import java.util.Map;

/**
 * @author: yinchao
 * @ClassName: DefaultAdvisorAutoProxyCreator
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/4/15 22:59
 */
public class DefaultAdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private DefaultListableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        // 判断是否为 aop 代理bean
        if (isInfrastructureClass(beanClass)) {
            return null;
        }
        Map<String, AspectJExpressionPointcutAdvisor> beansOfType = beanFactory.getBeansOfType(AspectJExpressionPointcutAdvisor.class);
        for (AspectJExpressionPointcutAdvisor advisor : beansOfType.values()) {
            // 通过 PointCut 来判断 bean 对象是否有切点，从而判断是否使用代理对象
            ClassFilter classFilter = advisor.getPointcut().getClassFilter();
            if (!classFilter.matches(beanClass)) {
                continue;
            }
            // 组装代理信息
            AdvisedSupport advisedSupport = new AdvisedSupport();
            TargetSource targetSource;
            try {
                targetSource = new TargetSource(beanClass.getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                throw new BeansException("Could not instantiate " + beanClass.getName(), e);
            }
            advisedSupport.setTargetSource(targetSource);
            advisedSupport.setMethodInterceptor((MethodInterceptor)advisor.getAdvice());
            advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
            advisedSupport.setProxyTargetClass(false);
            return new ProxyFactory(advisedSupport).getProxy();
        }

        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        return false;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        return null;
    }

    private boolean isInfrastructureClass(Class<?> beanClass) {
        return Advice.class.isAssignableFrom(beanClass) || PointCut.class.isAssignableFrom(beanClass) || Advisor.class.isAssignableFrom(beanClass);
    }


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
