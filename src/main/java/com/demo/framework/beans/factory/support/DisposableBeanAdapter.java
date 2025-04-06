package com.demo.framework.beans.factory.support;

import com.demo.framework.beans.BeansException;
import com.demo.framework.beans.factory.DisposableBean;
import com.demo.framework.beans.factory.InitializingBean;
import com.demo.framework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Method;

/**
 * @author: yinchao
 * @ClassName: DisposableBeanAdapter
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/4/6 23:50
 */
public class DisposableBeanAdapter implements DisposableBean {

    private Object bean;
    private String beanName;
    private String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    @Override
    public void destroy() throws Exception {
        // 1. 实现接口 InitializingBean
        if (bean instanceof DisposableBean) {
            ((DisposableBean) bean).destroy();
        }
        // 2. 配置信息 init-method
        if (!"".equals(destroyMethodName) && bean instanceof DisposableBean && destroyMethodName.equals("destroy")) {
            try {
                Method initMethod = bean.getClass().getMethod(destroyMethodName);
                initMethod.invoke(bean);
            } catch (Exception e) {
                throw new BeansException("Could not find an init method named '" + destroyMethodName + "' on bean with name'" + beanName + "'");
            }
        }
    }
}
