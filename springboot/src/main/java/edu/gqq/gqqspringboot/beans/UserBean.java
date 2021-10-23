package edu.gqq.gqqspringboot.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UserBean {

    Logger logger = LoggerFactory.getLogger(UserBean.class);
    private String name;
    private int id;

    public UserBean() {
        id = 133;
        name = "Peter";
        logger.debug("construct user bean.");
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
