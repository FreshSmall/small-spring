package com.test.framework;

import org.junit.Test;

import com.demo.framework.context.support.ClassPathXmlApplicationContext;
import com.test.framework.event.CustomEvent;

public class ApiTest {

    @Test
    public void test() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.publishEvent(new CustomEvent(applicationContext, 1019129009086763L, "成功了！")); 
    }
}
