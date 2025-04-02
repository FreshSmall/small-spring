package com.demo.framework.test;

import org.junit.Test;

import com.demo.framework.BeanDefinition;
import com.demo.framework.BeanFactory;
import com.demo.framework.test.bean.UserService;

/**
 * @author: yinchao
 * @ClassName: ApiTest
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/4/2 22:58
 */
public class ApiTest {

    @Test
    public void testBeanFactory() {
        // 1.初始化 BeanFactory
        BeanFactory beanFactory = new BeanFactory();
        
        // 2.注册 bean
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBean(new UserService());
        beanFactory.registerBeanDefinition("userService", beanDefinition);
        // 3.获取 bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        // 4.使用 bean
        userService.queryUserInfo();
    }
}
