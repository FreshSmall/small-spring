package com.test.framework.common;

import com.demo.framework.beans.BeansException;
import com.demo.framework.beans.factory.config.BeanPostProcessor;
import com.test.framework.bean.UserService;

/**
 * @author: yinchao
 * @ClassName: MyBeanPostProcessor
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/4/5 23:31
 */
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("初始化前 beanName:" + beanName);
        if (beanName.equals("userService")) {
            UserService userService = (UserService) bean;
            userService.setLocation("改为：北京");
            return userService;
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("初始化后beanName:" + beanName);
        return bean;
    }
}
