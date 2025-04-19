package com.test.framework.bean;

import com.demo.framework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author: yinchao
 * @ClassName: SpouseAdvice
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/4/19 23:28
 */
public class SpouseAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("关怀小两口(切面)：" + method);
    }
}
