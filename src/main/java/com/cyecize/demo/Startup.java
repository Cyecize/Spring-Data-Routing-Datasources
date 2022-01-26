package com.cyecize.demo;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class Startup {

    @EventListener(ApplicationReadyEvent.class)
    public void startUp() {
        System.out.println("App Started!");
    }
}
