package edu.gqq.beans;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceDemoBean {

    private static final Logger logger = LoggerFactory.getLogger(ServiceDemoBean.class);
    private IService service;

    public ServiceDemoBean() {
        logger.debug("calling ServiceDemoBean() constructor");
    }

    public ServiceDemoBean(IService serv) {
        logger.debug("calling ServiceDemoBean(service) constructor");
        this.service = serv;
    }

    public String getServiceValue() {
        return service.serve();
    }

    public IService getService() {
        return service;
    }

    public void setService(IService service) {
        logger.debug("calling setting service method.");
        this.service = service;
    }
}

