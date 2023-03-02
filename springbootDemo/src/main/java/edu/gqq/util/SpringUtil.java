package edu.gqq.util;

import java.util.Arrays;
import org.slf4j.Logger;

public final class SpringUtil {

    public static void printBean(String[] beanNames, Logger logger) {
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            logger.debug(beanName);
        }
    }
}
