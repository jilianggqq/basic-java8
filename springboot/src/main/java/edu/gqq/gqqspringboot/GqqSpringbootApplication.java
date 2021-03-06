package edu.gqq.gqqspringboot;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

//Configuration,EnableAutoConfiguration,ComponentScan
@SpringBootApplication
public class GqqSpringbootApplication {

    Logger logger = LoggerFactory.getLogger(GqqSpringbootApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(GqqSpringbootApplication.class, args);
    }

    /**
     * this runs on start up. It retrieves all the beans that were created by your application or that were
     * automatically added by Spring Boot. It sorts them and prints them out.
     */
    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

//            System.out.println("Let's inspect the beans provided by Spring Boot:");
            logger.debug("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
//                System.out.println(beanName);
                logger.debug(beanName);
            }
        };
    }
}