package com.test.framework;

import com.demo.framework.beans.PropertyValue;
import com.demo.framework.beans.PropertyValues;
import com.demo.framework.beans.factory.config.BeanDefinition;
import com.demo.framework.beans.factory.config.BeanReference;
import com.demo.framework.beans.factory.support.DefaultListableBeanFactory;
import com.test.framework.bean.UserDao;
import com.test.framework.bean.UserService;
import org.junit.Test;

public class ApiTest {

    @Test
    public void test_BeanFactory() {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 注册 userDao
        BeanDefinition beanDefinition = new BeanDefinition(UserDao.class);
        beanFactory.registerBeanDefinition("userDao", beanDefinition);

        // 增加propertyValue
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uId", "10001"));
        propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));
        // 2.注册 bean
        BeanDefinition beanDefinition_1 = new BeanDefinition(UserService.class, propertyValues);
        beanFactory.registerBeanDefinition("userService", beanDefinition_1);

        // 5. UserService 获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();

    }
}
