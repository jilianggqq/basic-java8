package edu.gqq.factory;

import edu.gqq.beans.IService;
import edu.gqq.beans.impl.IndexService;
import edu.gqq.beans.impl.MessageService;

public class StaticServiceFactory {
    public static IService getService(int number) {
        switch (number) {
            case 1:
                return new MessageService("Generated from STATIC factory.");
            case 0:
                return new IndexService();
            default:
                throw new IllegalArgumentException("Unknown parameter " + number);
        }
    }
}
