package com.test.framework.aop;

import com.demo.framework.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
* @author: yinchao
* @ClassName: AopTest
* @Description: 
* @team wuhan operational dev.
* @date: 2025/4/14 23:40
*/
public class AopTest {

    @Test
    public void test_dynamic() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        IUserService userService = applicationContext.getBean("userService", IUserService.class);
        System.out.println("测试结果：" + userService.queryUserInfo());
    }
}
