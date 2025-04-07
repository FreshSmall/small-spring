package com.demo.framework.beans.factory;

/**
 * @author: yinchao
 * @ClassName: BeanNameAware
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/4/7 23:23
 */
public interface BeanNameAware extends Aware {

    void setBeanName(String beanName);
}
