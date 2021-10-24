package edu.gqq.funcinterface;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FunctionTest {

    private static final Logger logger = LoggerFactory.getLogger(FunctionTest.class);

    @Test
    public void testThorough1() {
        List<String> names = Arrays.asList("Smith", "Gourav", "Heather", "John", "Catania");
        Function<String, Integer> getLen = s -> s.length();
        Function<Integer, String> getDesc = i -> "'s length is " + i;

        Consumer<Object> c = name -> {
            String strName = name.toString();
            String info = getLen.andThen(getDesc).apply(strName);
            logger.debug(name + info);
        };

        // the consumer c is Consumer<Object>. forEach parameter requirement is Consumer<? super String>.
        names.forEach(c);

        logger.info("----------------------test inline----------------------\n");
        names.forEach(x -> {
            logger.info(x + getLen.andThen(getDesc).apply(x));
        });
    }
}
