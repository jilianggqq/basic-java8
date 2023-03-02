package edu.gqq.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * https://winterbe.com/posts/2014/07/31/java8-stream-tutorial-examples/
 */
public class StreamCollectDemo {

    // Get actual class name to be printed on
    private static final Logger logger = LoggerFactory.getLogger(StreamCollectDemo.class);

    public static void main(String[] args) {
        connectorTest();
        System.out.println();
        buildOwnCollector();
    }

    @Test
    public void testOwnCollector() throws Exception {
        Collector<Person, StringBuilder, String> joinNameCollector = Collector.of(() -> new StringBuilder(),
            (sb, p) -> {
                logger.info(String.format("sb:%s, p:%s", sb.toString(), p));
                sb.append(" | ").append(p);
            },
            (sb1, sb2) -> {
                logger.info(String.format("sb1:%s, sb2:%s", sb1.toString(), sb2.toString()));
                return sb1.append(" | ").append(sb2.toString());
            },
            (StringBuilder sb) -> sb.toString()
        );

        List<Person> persons =
            Arrays.asList(
                new Person("Max", 18),
                new Person("Peter", 23),
                new Person("Yogesh", 23),
                new Person("Akash", 24),
                new Person("Satya", 24),
                new Person("Pamela", 23),
                new Person("David", 12));
        String people = persons.stream().collect(joinNameCollector);
        logger.debug(people);
    }

    private static void buildOwnCollector() {
        logger.info("---------------------- buildOwnCollector start ----------------------");
        Collector<Person, StringJoiner, String> joinNameCollector = Collector.of(
            () -> new StringJoiner(" | "), //supplier
            (joiner, p) -> {
                joiner.add(p.name.toUpperCase());
            }, // accumulator
            (j1, j2) -> j1.merge(j2), // combiner
            joiner -> joiner.toString() // finisher
        );
        List<Person> persons =
            Arrays.asList(
                new Person("Max", 18),
                new Person("Peter", 23),
                new Person("Yogesh", 23),
                new Person("Akash", 24),
                new Person("Satya", 24),
                new Person("Pamela", 23),
                new Person("David", 12));

        String names = persons.stream()
            .collect(joinNameCollector);

        logger.debug("all names: " + names);

        logger.info("---------------------- buildOwnCollector end ----------------------");

    }

    /**
     * test stream.collect() method.
     */
    private static void connectorTest() {
        logger.info("################### connectorTest start ###################");
        List<Person> persons =
            Arrays.asList(
                new Person("Max", 18),
                new Person("Peter", 23),
                new Person("Yogesh", 23),
                new Person("Pamela", 23),
                new Person("David", 12));

        List<Person> pPersons = persons.stream()
            .filter(p -> p.name.startsWith("P"))
            .collect(Collectors.toList());

        logger.debug(pPersons.toString());

        logger.info("---------------------- test groupingBy ----------------------");
        Map<Integer, List<Pair<Integer, String>>> groupRes = persons.stream()
            .map(p -> Pair.of(p.age, p.name))
            .collect(Collectors.groupingBy(x -> x.getLeft()));

        logger.debug("group res: " + groupRes.toString());

        logger.info("---------------------- test joining ----------------------");
        String legalInfo = persons.stream()
            .filter(p -> p.age >= 18)
            .map(x -> x.name)
            .collect(Collectors.joining(",", "", " are in legal age."));
        logger.debug("legalInfo: " + legalInfo);

        logger.info("---------------------- test toMap ----------------------");
        Map<Integer, String> listToMap = persons.stream()
            .collect(Collectors.toMap(p -> p.age, p -> p.name, (p1, p2) -> p1 + ";" + p2));

        logger.debug("listToMap: " + listToMap.toString());

        logger.info("---------------------- test partitioningBy ----------------------");
        Map<Boolean, List<Person>> partPeople = persons.stream()
            .collect(Collectors.partitioningBy(x -> x.age > 18));
        partPeople.forEach((k, v) -> {
            List<String> names = v.stream().map(p -> p.name).collect(Collectors.toList());
            logger.debug(k + " : " + names);
        });

        logger.info("################### connectorTest end ###################");
    }

}