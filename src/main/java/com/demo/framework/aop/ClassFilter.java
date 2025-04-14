package com.demo.framework.aop;/**
* @author: yinchao
* @ClassName: ClassFilter
* @Description: 
* @team wuhan operational dev.
* @date: 2025/4/14 23:18
*/
public interface ClassFilter {

    /**
     * Should the pointcut apply to the given interface or target class?
     * @param clazz the candidate target class
     * @return whether the advice should apply to the given target class
     */
    boolean matches(Class<?> clazz);
}
