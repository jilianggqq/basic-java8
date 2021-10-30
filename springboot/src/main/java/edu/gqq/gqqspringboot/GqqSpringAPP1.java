package edu.gqq.gqqspringboot;

import edu.gqq.util.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"edu.gqq.beans"})
public class GqqSpringAPP1 {

    Logger logger = LoggerFactory.getLogger(GqqSpringAPP1.class);

    public static void main(String[] args) {
        SpringApplication.run(GqqSpringAPP1.class, args);
    }

    /**
     * this runs on start up. It retrieves all the beans that were created by your application or that were
     * automatically added by Spring Boot. It sorts them and prints them out.
     */
    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            logger.debug("Let's inspect the beans provided by Spring Boot:");
            String[] beanNames = ctx.getBeanDefinitionNames();
            SpringUtil.printBean(beanNames, logger);
        };
    }
}
