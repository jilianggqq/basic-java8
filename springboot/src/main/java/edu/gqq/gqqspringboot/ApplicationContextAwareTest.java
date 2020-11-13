package edu.gqq.gqqspringboot;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextAwareTest implements ApplicationContextAware {

    ApplicationContext context;

    public ApplicationContext getContext() {
        return this.context;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
