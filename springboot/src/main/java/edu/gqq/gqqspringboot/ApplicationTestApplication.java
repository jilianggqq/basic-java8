package edu.gqq.gqqspringboot;

import edu.gqq.gqqspringboot.beans.AnyBean;
import edu.gqq.util.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationTestApplication {
    static Logger logger = LoggerFactory.getLogger(ApplicationTestApplication.class);

    public static void main(String[] args) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("app-conf.xml");
        ApplicationContextAwareTest appcontext = (ApplicationContextAwareTest) context.getBean("appcontext");
        ApplicationContext appCon = appcontext.getContext();
        AnyBean a = (AnyBean) appCon.getBean("anybean");

        a.doTask();
        context.registerShutdownHook();

        logger.debug("Let's inspect the beans provided by Spring Boot:");

        String[] beanNames = appCon.getBeanDefinitionNames();
        SpringUtil.printBean(beanNames, logger);
    }
}
