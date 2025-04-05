package com.test.framework;

import com.demo.framework.beans.PropertyValue;
import com.demo.framework.beans.PropertyValues;
import com.demo.framework.beans.factory.config.BeanDefinition;
import com.demo.framework.beans.factory.config.BeanReference;
import com.demo.framework.beans.factory.support.DefaultListableBeanFactory;
import com.demo.framework.core.io.DefaultResourceLoader;
import com.demo.framework.core.io.Resource;
import com.test.framework.bean.UserDao;
import com.test.framework.bean.UserService;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class ApiTest {

    @Test
    public void test_BeanFactory() {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 注册 userDao
        BeanDefinition beanDefinition = new BeanDefinition(UserDao.class);
        beanFactory.registerBeanDefinition("userDao", beanDefinition);

        // 增加propertyValue
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uId", "10001"));
        propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));
        // 2.注册 bean
        BeanDefinition beanDefinition_1 = new BeanDefinition(UserService.class, propertyValues);
        beanFactory.registerBeanDefinition("userService", beanDefinition_1);

        // 5. UserService 获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();

    }

    @Test
    public void test_classpath() throws IOException {
        // 创建资源加载器
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();

        // 加载 classpath 资源
        Resource resource = resourceLoader.getResource("classpath:important.properties");
        System.out.println("Classpath Resource: " + resource.getClass());
        printResourceContent(resource);
    }

    @Test
    public void test_file() throws IOException {
        // 创建资源加载器
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();

        // 加载文件系统资源
        // 获取当前项目根路径
        String rootPath = new File("").getAbsolutePath();
        Resource resource = resourceLoader.getResource(rootPath + "/src/test/resources/important.properties");
        System.out.println("File System Resource: " + resource.getClass());
        printResourceContent(resource);
    }

    @Test
    public void test_url() throws IOException {
        // 创建资源加载器
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
        // 加载 URL 资源
        // 这里使用 GitHub 上的一个公开文件作为示例
        Resource resource = resourceLoader.getResource("https://raw.githubusercontent.com/fuzhengwei/small-spring/main/README.md");
        System.out.println("URL Resource: " + resource.getClass());
        printResourceContent(resource);
    }

    private void printResourceContent(Resource resource) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            String line;
            System.out.println("Resource Content:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
}
