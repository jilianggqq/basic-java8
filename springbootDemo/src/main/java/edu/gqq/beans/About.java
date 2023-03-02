package edu.gqq.beans;

import org.springframework.stereotype.Component;

@Component
public class About {

    private String msg;

    public About() {
        msg = "Welcome MSG!";
    }

    public String getMsg() {
        return msg;
    }
}
