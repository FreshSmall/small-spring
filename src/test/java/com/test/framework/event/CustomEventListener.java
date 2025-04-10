package com.test.framework.event;

import com.demo.framework.context.ApplicationListener;

public class CustomEventListener implements ApplicationListener<CustomEvent> {

    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println("Received custom event - " + event.getMessage()); 
    }
}
