package edu.gqq.funcinterface;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PredicateTest {

    private static final Logger logger = LoggerFactory.getLogger(PredicateTest.class);

    @Test
    public void thoroughTest() {
        Predicate<String> p1 = s -> s.startsWith("S");
        Predicate<String> p2 = s -> s.length() >= 5;
        Predicate<String> p3 = s -> s.contains("God");

        Predicate<String> finalP = (p1.and(p2)).or(p3);

        List<String> names = Arrays.asList("John", "Smith", "Samueal", "Catley", "Sie", "AGod", "SGod",
            "Agod", "Sgod", "SgodJohn");

        names.forEach(name -> {
            logger.debug(String.format("name: %s, test_result: %s", name, finalP.test(name)));
        });

        List<String> filteredNames = names.stream().filter(finalP).collect(Collectors.toList());
        logger.info(filteredNames.toString());
    }
}
