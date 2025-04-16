package com.demo.framework.context.annotation;

import cn.hutool.core.util.ClassUtil;
import com.demo.framework.beans.factory.config.BeanDefinition;
import com.demo.framework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author: yinchao
 * @ClassName: ClassPathScanningCandidateComponentProvider
 * @Description: 处理对象扫描装配
 * @team wuhan operational dev.
 * @date: 2025/4/16 23:00
 */
public class ClassPathScanningCandidateComponentProvider {

    public Set<BeanDefinition> findCandidateComponents(String basePackage) {
        Set<BeanDefinition> candidates = new LinkedHashSet<>();
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(basePackage, Component.class);
        for (Class<?> clazz : classes) {
            candidates.add(new BeanDefinition(clazz));
        }
        return candidates;
    }
}
