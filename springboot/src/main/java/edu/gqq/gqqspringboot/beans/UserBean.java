package edu.gqq.gqqspringboot.beans;

import org.springframework.stereotype.Component;

@Component
public class UserBean {

    private String name;
    private int id;

    public UserBean() {
        id = 133;
        name = "Peter";
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
