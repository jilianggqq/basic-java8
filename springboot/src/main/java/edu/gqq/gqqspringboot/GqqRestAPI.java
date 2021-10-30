package edu.gqq.gqqspringboot;

import edu.gqq.beans.About;
import edu.gqq.beans.UserBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GqqRestAPI {

    Logger logger = LoggerFactory.getLogger(GqqRestAPI.class);

    @Autowired
    UserBean userBean;

    @Autowired
    About about;

    @RequestMapping("/")
    public String index() {
        logger.debug("GqqRestAPI is requested!");
        return "Greetings from Spring Boot! " + userBean.getName();
    }

    @RequestMapping("/about")
    public String about() {
        return about.getMsg();
    }
}
