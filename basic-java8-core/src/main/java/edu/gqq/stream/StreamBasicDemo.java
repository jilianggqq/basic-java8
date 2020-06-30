package edu.gqq.stream;

import java.time.Duration;
import java.time.Instant;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.apache.log4j.Logger;

/**
 * https://winterbe.com/posts/2014/07/31/java8-stream-tutorial-examples/
 */
public class StreamBasicDemo {

    // Get actual class name to be printed on
    private static final Logger logger = Logger.getLogger(StreamBasicDemo.class);

    public static void main(String[] args) {
        logger.info("****** main start *******");
        //1. IntStreams can replace the regular for-loop utilizing IntStream.range()
        IntStream.range(1, 5).forEach(x -> logger.debug(x));

        // 2. Sometimes it's useful to transform a regular object stream to a primitive stream or vice versa.
        // For that purpose object streams support the special mapping operations mapToInt(), mapToLong() and mapToDouble:
        Stream.of("a1", "a100", "a3")
            .map(str -> str.substring(1))
            .mapToInt(Integer::parseInt)
            .max()
            .ifPresent(x -> logger.debug(x));

        // 3. Primitive streams can be transformed to object streams via mapToObj():
        IntStream.range(5, 8)
            .mapToObj(x -> x + "s ")
            .forEach(logger::debug);
        System.out.println();
        // 4。 vertically moving.This behavior can reduce the actual number of operations performed on each element
        // map :: d2
        //anyMatch :: D2
        //map :: a2
        //anyMatch :: A2
        Stream.of("d2", "a2", "b1", "b3", "c")
            .map(s -> {
                logger.debug("map :: " + s);
                return s.toUpperCase();
            })
            .anyMatch(s -> {
                logger.debug("anyMatch :: " + s);
                return s.startsWith("A");
            });

        // 5. order is important.
        orderIsImportant();

        // 6. Reusing Streams
        resueStream();
        logger.info("****** main end *******");
    }

    /**
     * Java 8 streams cannot be reused. <br>we could create a stream supplier to construct a new stream with
     * all intermediate operations already set up:
     */
    private static void resueStream() {
        Supplier<Stream<String>> streamSupplier = () -> Stream.of("d2", "a2", "b1", "b3", "c");
        streamSupplier.get().filter(x -> x.startsWith("b")).findFirst().ifPresent(x -> logger.debug(x));
        boolean res = streamSupplier.get().anyMatch(x -> x.contains("3"));
        logger.debug(res);
    }

    /**
     * Sorting is a special kind of intermediate operation. It's a so called <b>stateful operation</b> since
     * in order to sort a collection of elements you have to maintain state during ordering
     */
    private static void orderIsImportant() {
        logger.info("****** orderIsImportant start *******");

        Instant start = Instant.now();
        Stream.of("d2", "a2", "b1", "b3", "c", "d2", "a2", "b1", "b3", "c", "d2", "a2", "b1", "b3", "c")
            .sorted((s1, s2) -> {
                logger.debug("sorted :: " + s1 + " " + s2);
                return s1.compareTo(s2);
            })
            .map(s -> {
                logger.debug("map :: " + s);
                return s.toUpperCase();
            })
            .filter(s -> {
                logger.debug("filter :: " + s);
                return s.startsWith("A");
            })
            .forEach(x -> logger.debug(x));

        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        logger.debug("1st time elapsed: " + timeElapsed.getNano());

        Instant start1 = Instant.now();
        Stream.of("d2", "a2", "b1", "b3", "c", "d2", "a2", "b1", "b3", "c", "d2", "a2", "b1", "b3", "c")
            .filter(s -> {
                logger.debug("filter :: " + s);
                return s.startsWith("a");
            })
            .sorted((s1, s2) -> {
                logger.debug("sorted :: " + s1 + " " + s2);
                return s1.compareTo(s2);
            })
            .map(s -> {
                logger.debug("map :: " + s);
                return s.toUpperCase();
            })
            .forEach(x -> logger.debug(x));

        Instant end1 = Instant.now();
        Duration timeElapsed1 = Duration.between(start1, end1);
        logger.debug("2st time elapsed: " + timeElapsed1.getNano());
        logger.info("****** orderIsImportant end *******");
    }
}
