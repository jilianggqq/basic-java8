package edu.gqq.beans.impl;


import edu.gqq.beans.IService;

public class MessageService implements IService {

    private String message;

    public MessageService(String message) {
        this.message = message;
    }

    @Override
    public String serve() {
        return "*** Message Service: " + message + " ***";
    }

}
