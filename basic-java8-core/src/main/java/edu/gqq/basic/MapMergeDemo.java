package edu.gqq.basic;

import java.util.HashMap;
import java.util.function.BiFunction;
import org.apache.log4j.Logger;

public class MapMergeDemo {

    private static final Logger logger = Logger.getLogger(MapMergeDemo.class);

    public static void main(String[] args) {
        // create a HashMap and add some values
        HashMap<Integer, String> map1 = new HashMap<>();
        map1.put(1, "Ram");
        map1.put(2, "Rohan");
        map1.put(3, "Shivam");

        HashMap<Integer, String> map2 = new HashMap<>();
        map2.put(1, "Tushar");
        map2.put(10, "Satya");
        map2.put(12, "Sundar");

        BiFunction<String, String, String> biFunction = (v1, v2) -> v1 + " | " + v2;

        map2.merge(3, "Akash", biFunction);
        logger.debug(map2);
        map2.merge(10, "Sharmin", biFunction);
        logger.debug(map2);

        map1.forEach((key, val) -> {
            map2.merge(key, val, biFunction);
        });
        logger.debug(map2);
    }
}
