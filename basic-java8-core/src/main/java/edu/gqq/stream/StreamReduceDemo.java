package edu.gqq.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * https://winterbe.com/posts/2014/07/31/java8-stream-tutorial-examples/
 */

/**
 * Map is kinda limited because every object can only be mapped to exactly one other object.
 *
 * But what if we want to transform one object into multiple others or none at all?
 *
 * This is where flatMap comes to the rescue.
 */
public class StreamReduceDemo {

    // Get actual class name to be printed on
    private static final Logger logger = LoggerFactory.getLogger(StreamReduceDemo.class);

    public static void main(String[] args) {
        List<Person> persons =
            Arrays.asList(
                new Person("Max", 18),
                new Person("Peter", 23),
                new Person("Yogesh", 23),
                new Person("Akash", 24),
                new Person("Satya", 24),
                new Person("Pamela", 23),
                new Person("David", 12));

        persons.stream()
            .reduce((p1, p2) -> {
                return p1.age < p2.age ? p1 : p2;
            })
            .ifPresent(person -> logger.debug(person.name));

        // If persons2 is empty, then returns new Person("Default", 100).
        // If persons2 is NOT empty, then executing apply method for result and Person.
        List<Person> persons2 = new ArrayList<>();
        Person reducePerson = persons2.stream()
            .reduce(new Person("Default", 100), (p1, p2) -> {
//                return new Person(p1.name + p2.name, p1.age + p2.age);
//                the same as:
                p1.age += p2.age;
                p1.name += p2.name;
                return p1;
            });

        logger.debug(reducePerson.name + " " + reducePerson.age);
    }


}

