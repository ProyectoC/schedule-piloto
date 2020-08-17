package com.schedulepiloto.core.config;

import com.schedulepiloto.core.audit.AuditorInterceptor;
import com.schedulepiloto.core.interceptor.SchedulePilotoInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import reactor.Environment;
import reactor.bus.EventBus;

@Configuration
public class AppConfig implements WebMvcConfigurer {

    @Autowired
    private SchedulePilotoInterceptor schedulePilotoInterceptor;

    @Autowired
    private AuditorInterceptor auditorInterceptor;

    @Bean
    public Environment env() {
        return Environment.initializeIfEmpty().assignErrorJournal();
    }

    @Bean
    public EventBus createEventBus(Environment env) {
//        EventBus evBus = EventBus.create(env, Environment.newDispatcher(REACTOR_CAPACITY,REACTOR_CONSUMERS_COUNT,
//                DispatcherType.THREAD_POOL_EXECUTOR));
        return EventBus.create(env, Environment.THREAD_POOL);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(schedulePilotoInterceptor);
        registry.addInterceptor(auditorInterceptor);
    }
}
