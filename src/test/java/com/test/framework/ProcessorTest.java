package com.test.framework;

import com.demo.framework.beans.factory.support.DefaultListableBeanFactory;
import com.demo.framework.beans.factory.xml.XmlBeanDefinitionReader;
import com.demo.framework.context.support.ClassPathXmlApplicationContext;
import com.test.framework.bean.UserService;
import com.test.framework.common.MyBeanFactoryPostProcessor;
import com.test.framework.common.MyBeanPostProcessor;
import org.junit.Test;

/**
 * @author: yinchao
 * @ClassName: ProcessorTest
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/4/5 23:35
 */
public class ProcessorTest {

    @Test
    public void test_prototype() {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        // 2. 获取Bean对象调用方法
        UserService userService01 = applicationContext.getBean("userService", UserService.class);
        UserService userService02 = applicationContext.getBean("userService", UserService.class);

        // 3. 配置 scope="prototype/singleton"
        System.out.println(userService01);
        System.out.println(userService02);

        // 4. 打印十六进制哈希
        System.out.println(userService01 + " 十六进制哈希：" + Integer.toHexString(userService01.hashCode()));
        System.out.println(userService02 + " 十六进制哈希：" + Integer.toHexString(userService02.hashCode()));
    }

    @Test
    public void test_factory_bean() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();
        UserService userService = applicationContext.getBean("userService", UserService.class);
        String msg = userService.queryUserInfo();
        System.out.println("测试结果，msg=" + msg);
    }


}
