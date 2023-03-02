package edu.gqq.factory;

import edu.gqq.beans.IService;
import edu.gqq.beans.impl.IndexService;
import edu.gqq.beans.impl.MessageService;

public class InstanceServiceFactory {

    public IService getService(int number) {
        switch (number) {
            case 1:
                return new MessageService("Generated from INSTANCE factory.");
            case 0:
                return new IndexService();
            default:
                throw new IllegalArgumentException("Unknown parameter " + number);
        }
    }
}
