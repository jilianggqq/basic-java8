package edu.gqq.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import org.apache.log4j.Logger;

/**
 * https://winterbe.com/posts/2014/07/31/java8-stream-tutorial-examples/
 */
public class StreamCollectDemo {

    // Get actual class name to be printed on
    private static final Logger logger = Logger.getLogger(StreamCollectDemo.class);

    public static void main(String[] args) {
        connectorTest();

        buildOwnCollector();
    }

    private static void buildOwnCollector() {
        logger.info("################### buildOwnCollector start ###################");
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

        logger.info("################### buildOwnCollector end ###################");

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

        logger.debug(pPersons);

        logger.info("################### test groupingBy ###################");
        Map<Integer, List<Person>> personsByAge = persons.stream()
            .collect(Collectors.groupingBy(x -> x.age));

        personsByAge.forEach((age, ps) -> {
            logger.debug(age + ":" + ps);
        });

        logger.info("################### test joining ###################");
        String legalInfo = persons.stream()
            .filter(p -> p.age >= 18)
            .map(x -> x.name)
            .collect(Collectors.joining(",", "", " are in legal age."));
        logger.debug(legalInfo);

        logger.info("################### test toMap ###################");
        Map<Integer, String> listToMap = persons.stream()
            .collect(Collectors.toMap(p -> p.age, p -> p.name, (p1, p2) -> p1 + ";" + p2));

        logger.debug(listToMap);

        logger.info("################### test partitioningBy ###################");
        Map<Boolean, List<Person>> partPeople = persons.stream()
            .collect(Collectors.partitioningBy(x -> x.age > 18));
        partPeople.forEach((k, v) -> {
            List<String> names = v.stream().map(p -> p.name).collect(Collectors.toList());
            logger.debug(k + " : " + names);
        });

        logger.info("################### connectorTest end ###################");
    }

}

class Person {

    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return name;
    }
}