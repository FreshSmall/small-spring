package com.test.framework.common;

import com.demo.framework.beans.BeansException;
import com.demo.framework.beans.PropertyValue;
import com.demo.framework.beans.PropertyValues;
import com.demo.framework.beans.factory.ConfigurableListableBeanFactory;
import com.demo.framework.beans.factory.config.BeanDefinition;
import com.demo.framework.beans.factory.config.BeanFactoryPostProcessor;

/**
 * @author: yinchao
 * @ClassName: MyFactoryBeanPostProcessor
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/4/5 23:30
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        BeanDefinition userService = beanFactory.getBeanDefinition("userService");
        // 修改 bean 中的属性
        PropertyValues propertyValues = userService.getPropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("company", "改为：字节跳动"));
    }
}
