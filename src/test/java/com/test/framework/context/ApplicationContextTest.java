package com.test.framework.context;

import com.demo.framework.context.support.ClassPathXmlApplicationContext;
import com.test.framework.bean.UserService;
import org.junit.Test;

/**
 * 应用上下文测试类
 *
 * @author: yinchao
 * @ClassName: ApplicationContextTest
 * @Description: 测试应用上下文的功能
 * @team wuhan operational dev.
 * @date: 2025/4/5 23:56
 */
public class ApplicationContextTest {

    @Test
    public void testApplicationContext() {
        // 1. 创建应用上下文
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        
        // 2. 获取Bean对象调用方法
        UserService userService = (UserService) applicationContext.getBean("userService");
        userService.queryUserInfo();
    }
}
