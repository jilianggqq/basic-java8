package edu.gqq.beans.impl;

import edu.gqq.beans.IService;

public class IndexService implements IService {

    @Override
    public String serve() {
        return "*** Index Service ***";
    }

}