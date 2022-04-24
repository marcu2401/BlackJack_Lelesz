package hu.BlackJack.config.impl;

import com.google.common.eventbus.EventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventBusConfiguration {

    public EventBusConfiguration() {
        System.out.println("EventbusConfiguration  created");
    }

    @Bean
    EventBus eventBus(){
        return new EventBus();
    }
}
