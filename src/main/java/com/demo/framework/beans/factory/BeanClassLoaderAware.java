package com.demo.framework.beans.factory;

import com.demo.framework.beans.BeansException;

/**
 * @author: yinchao
 * @ClassName: BeanClassLoaderAware
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/4/7 23:22
 */
public interface BeanClassLoaderAware extends Aware {

    void setBeanClazzLoader(ClassLoader loader);
}
