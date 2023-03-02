package edu.gqq.funcinterface;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * T is the type of items in the stream to be collected <br/> A is the type of the accumulator R is the type
 * of the result returned by the collector
 *
 * https://blog.indrek.io/articles/creating-a-collector-in-java-8/
 */
public class CustomCollector {

    private static final Logger logger = LoggerFactory.getLogger(CustomCollector.class);

    public static HistogramCollector toHistogram(int bucketSize) {
        return new HistogramCollector(bucketSize);
    }

    public static void main(String[] args) {
        List<Double> numbers = Arrays.asList(1.0, 1.1, 1.4, 1.7, 1.4, 5.4, 9.9);
        Map<Integer, Integer> integerMap = numbers.stream()
            .collect(toHistogram(1));

        logger.debug(integerMap.toString());
    }
}

class HistogramCollector
    implements Collector<Double, Map<Integer, Integer>, Map<Integer, Integer>> {

    private int bucketSize;

    public HistogramCollector(int bucketSize) {
        this.bucketSize = bucketSize;
    }

    /**
     * First of all, the supplier() method needs to return a function which returns an empty accumulator.
     */
    @Override
    public Supplier<Map<Integer, Integer>> supplier() {
        return () -> new HashMap<>();
    }

    /**
     * stream elements are being accumulated by the function returned by the accumulator() method.
     */
    @Override
    public BiConsumer<Map<Integer, Integer>, Double> accumulator() {
        return (map, val) -> {
            map.merge((int) (val / bucketSize), 1, (a, b) -> a + 1);
        };
    }

    /**
     * When the stream is collected in parallel then the combiner() method is used to return a function which
     * knows how to merge two accumulators.
     *
     * This can happen when you process the stream in parallel.
     */
    @Override
    public BinaryOperator<Map<Integer, Integer>> combiner() {
        return (m1, m2) -> {
            m1.forEach((k, v) -> m2.merge(k, v, (v1, v2) -> v1 + v2));
            return m2;
        };
    }

    /**
     * In mathematics, an identity function, also called an identity relation or identity map or identity
     * transformation, is a function that always returns the same value that was used as its argument –
     * Wikipedia
     */
    @Override
    public Function<Map<Integer, Integer>, Map<Integer, Integer>> finisher() {
        //return Function.identity();
        return x -> x;
    }

    @Override
    public Set<Characteristics> characteristics() {
//        return null;
        Set<Characteristics> set = new HashSet<>();
        set.add(Characteristics.IDENTITY_FINISH);
        return set;
    }
}