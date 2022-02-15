package com.demo.framework.beans;

import java.util.ArrayList;
import java.util.List;

public class PropertyValues {

    private final List<PropertyValue> list = new ArrayList<>();

    public void addPropertyValue(PropertyValue propertyValue) {
        this.list.add(propertyValue);
    }

    public PropertyValue getPropertyValue(String beanName) {
        for (PropertyValue propertyValue : list) {
            if (propertyValue.getName().equals(beanName)) {
                return propertyValue;
            }
        }
        return null;
    }

    public List<PropertyValue> getList() {
        return list;
    }
}
