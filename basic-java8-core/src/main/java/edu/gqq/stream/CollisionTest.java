package edu.gqq.stream;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

public class CollisionTest {

}

interface Processor {
    String process(Callable<String> c) throws Exception;

    String process(Supplier<String> s);
}

class ProcessorImpl implements Processor {
    @Override
    public String process(Callable<String> c) throws Exception {
        // implementation details
        return "aaa";
    }

    @Override
    public String process(Supplier<String> s) {
        // implementation details
        return "bbb";
    }
}