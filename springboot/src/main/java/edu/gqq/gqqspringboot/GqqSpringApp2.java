package edu.gqq.gqqspringboot;

import edu.gqq.beans.AnyBean;
import edu.gqq.beans.ServiceDemoBean;
import edu.gqq.springhelper.ApplicationContextAwareTest;
import edu.gqq.util.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GqqSpringApp2 {

    static Logger logger = LoggerFactory.getLogger(GqqSpringApp2.class);

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        ApplicationContextAwareTest appcontext = (ApplicationContextAwareTest) context.getBean("appcontext");
        ApplicationContext appCon = appcontext.getContext();

        // here, context and appCon Should be the same.
        logger.debug(String.format("appcon == context? %s", context == appCon));

        testGetBean1((ClassPathXmlApplicationContext) appCon);
        System.out.println();
        testGetBean2(context);
        testGetBean21(context);

        testGenerateBeanFromStaticFactory(context);
        testGenerateBeanFromInstanceFactory(context);

        summaryTest(context);
    }

    private static void testGenerateBeanFromInstanceFactory(ClassPathXmlApplicationContext ctx) {
        System.out.println();
        logger.info(
            "---------------------4.-testGenerateBeanFromInstanceFactory start:: "
                + "messageServiceFromStaticFactory----------------------");
        ServiceDemoBean msgApp = (ServiceDemoBean) ctx.getBean("msgAppWithInstanceFactory");
        String res = msgApp.getService().serve();
        logger.debug("serve result is: " + res);

        logger.info("---------------------4.-testGenerateBeanFromInstanceFactory "
            + "end----------------------\n");

    }

    private static void testGenerateBeanFromStaticFactory(ClassPathXmlApplicationContext ctx) {
        System.out.println();
        logger.info(
            "----------------------3. testGenerateBeanFromStaticFactory start:: "
                + "messageServiceFromStaticFactory----------------------");
        ServiceDemoBean msgApp = (ServiceDemoBean) ctx.getBean("msgAppWithStaticFactory");
        String res = msgApp.getService().serve();
        logger.debug("serve result is: " + res);

        logger.info("----------------------3. testGenerateBeanFromStaticFactory end----------------------\n");
    }

    private static void summaryTest(ClassPathXmlApplicationContext appCon) {
        System.out.println();
        logger.info("----------------------summary start----------------------");
        logger.debug("Let's inspect the beans provided by Spring Boot:");
        appCon.registerShutdownHook();
        String[] beanNames = appCon.getBeanDefinitionNames();
        SpringUtil.printBean(beanNames, logger);
        logger.info("----------------------summary end----------------------\n");
    }

    private static void testGetBean2(ClassPathXmlApplicationContext context) {
        System.out.println();
        logger.info("----------------------2.test SET bean injection----------------------");
        ServiceDemoBean indexApp = (ServiceDemoBean) context.getBean("indexApp");
        String res = indexApp.getService().serve();
        logger.debug("serve result is: " + res);
        logger.info("----------------------2.test SET bean injection end----------------------\n");
    }

    private static void testGetBean21(ClassPathXmlApplicationContext context) {
        System.out.println();
        logger.info("----------------------2.1.test CONSTRUCTOR injection----------------------");
        ServiceDemoBean indexApp = (ServiceDemoBean) context.getBean("indexApp");
        String res = indexApp.getService().serve();
        logger.debug("serve result is: " + res);
        logger.info("----------------------2.1.test CONSTRUCTOR injection end----------------------\n");
    }

    private static void testGetBean1(ClassPathXmlApplicationContext appCon) {
        System.out.println();
        logger.info("----------------------1.test read bean from xml----------------------");
        AnyBean a = (AnyBean) appCon.getBean("anybean");
        a.doTask();
        logger.info("----------------------1.test read bean from xml end----------------------\n");
    }


}
