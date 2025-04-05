package com.demo.framework.beans.factory.xml;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import cn.hutool.core.util.StrUtil;
import com.demo.framework.beans.PropertyValue;
import com.demo.framework.beans.factory.config.BeanReference;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.demo.framework.beans.BeansException;
import com.demo.framework.beans.factory.config.BeanDefinition;
import com.demo.framework.beans.factory.support.AbstractBeanDefinitionReader;
import com.demo.framework.beans.factory.support.BeanDefinitionRegistry;
import com.demo.framework.core.io.Resource;
import com.demo.framework.core.io.ResourceLoader;

/**
 * @author: yinchao
 * @ClassName: XmlBeanDefinitionReader
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/4/3 23:34
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {
        try {
            try (InputStream inputStream = resource.getInputStream()) {
                doLoadBeanDefinitions(inputStream);
            }
        } catch (IOException | ClassNotFoundException | ParserConfigurationException | SAXException e) {
            throw new BeansException("解析XML文件失败 [" + resource + "]", e);
        }
    }

    @Override
    public void loadBeanDefinitions(Resource... resources) throws BeansException {
        for (Resource resource : resources) {
            loadBeanDefinitions(resource);
        }
    }

    @Override
    public void loadBeanDefinitions(String location) throws BeansException {
        Resource resource = getResourceLoader().getResource(location);
        loadBeanDefinitions(resource);
    }

    /**
     * 从输入流中加载Bean定义
     */
    protected void doLoadBeanDefinitions(InputStream inputStream) throws ClassNotFoundException, ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = factory.newDocumentBuilder();
        Document doc = docBuilder.parse(inputStream);

        // 解析XML文档的bean元素
        Element root = doc.getDocumentElement();
        NodeList nl = root.getElementsByTagName("bean");

        for (int i = 0; i < nl.getLength(); i++) {
            Element ele = (Element) nl.item(i);
            // 获取bean的Class全限定名
            String className = ele.getAttribute("class");
            // 获取bean的id
            String id = ele.getAttribute("id");
            // 如果没有指定id，使用类名的第一个字母小写作为id
            String beanName = id.isEmpty() ? lowerFirst(className.substring(className.lastIndexOf('.') + 1)) : id;

            // 创建Bean定义对象
            Class<?> clazz = Class.forName(className);
            BeanDefinition beanDefinition = new BeanDefinition(clazz);
            // 获取 xml 中的属性信息
            NodeList childNodes = ele.getChildNodes();
            for (int j = 0; j < childNodes.getLength(); j++) {
                // 只处理 Element 类型的节点
                if (!(childNodes.item(j) instanceof Element)) {
                    continue;
                }
                Element node = (Element) childNodes.item(j);
                if (!node.getNodeName().equals("property")) {
                    continue;
                }
                // 解析标签：property
                String propertyName = node.getAttribute("name");
                String propertyValue = node.getAttribute("value");
                String ref = node.getAttribute("ref");
                // 设置引入值对象
                // 获取属性值：引入对象、值对象
                Object value = StrUtil.isNotEmpty(ref) ? new BeanReference(ref) : propertyValue;
                // 设置属性值
                PropertyValue propertyValueBean = new PropertyValue(propertyName, value);
                beanDefinition.getPropertyValues().addPropertyValue(propertyValueBean);
            }
            // 注册Bean定义
            getRegistry().registerBeanDefinition(beanName, beanDefinition);
        }
    }

        /**
         * 将字符串的首字母转换为小写
         */
        private String lowerFirst (String str){
            if (str == null || str.isEmpty()) {
                return str;
            }
            char[] chars = str.toCharArray();
            chars[0] = Character.toLowerCase(chars[0]);
            return new String(chars);
        }
    }
