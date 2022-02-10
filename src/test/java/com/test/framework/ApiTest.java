package com.test.framework;

import com.demo.framework.factory.config.BeanDefinition;
import com.demo.framework.factory.support.DefaultListableBeanFactory;
import com.test.framework.bean.UserService;
import org.junit.Test;

public class ApiTest {

    @Test
    public void test_BeanFactory(){
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2.注册 bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 4.第二次获取 bean from Singleton
        UserService userService_singleton = (UserService) beanFactory.getBean("userService","测试地址");
        userService_singleton.queryUserInfo();
    }
}
