package edu.gqq.stream;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * https://winterbe.com/posts/2014/07/31/java8-stream-tutorial-examples/
 */

/**
 * Map is kinda limited because every object can only be mapped to exactly one other object.
 *
 * But what if we want to transform one object into multiple others or none at all?
 *
 * This is where flatMap comes to the rescue.
 */
public class StreamFlatMapDemo {

    // Get actual class name to be printed on
    private static final Logger logger = LoggerFactory.getLogger(StreamFlatMapDemo.class);

    public static void main(String[] args) {
        logger.info("################### mapObject start ###################");
        mapObject();

        logger.info("################### mapObject2 start ###################");
        mapObject2();

        logger.info("################### testNoNullCheck start ###################");
        testNoNullCheck();
    }

    /**
     * we can use Optional.ofNullable to skip null check.
     */
    private static void testNoNullCheck() {
        Outer outer = new Outer();
        Nested nested2 = new Nested();
        nested2.inner = new Inner();
        outer.nested = nested2;
        nested2.inner.foo = "foo";
        Optional<Inner> inner = Optional.of(outer)
            .flatMap(outer1 -> Optional.ofNullable(outer1.nested))
            .flatMap(nested -> Optional.ofNullable(nested.inner));

        inner.ifPresent(inner1 -> logger.debug(inner1.foo));

        // 1. test Optional.empty()
        Optional.empty().flatMap(x -> {
            logger.debug("Not called");
            return null;
        });

        // 2. detailed Debug
        Optional<Inner> inner1 = Optional.of(outer)
            .flatMap(outer1 -> {
                logger.debug("map to outer1.nested");
                return Optional.ofNullable(outer1.nested);
            })
            .flatMap(nested -> {
                logger.debug("map to nested.inner");
                return Optional.ofNullable(nested.inner);
            });
        inner1.ifPresent(v -> logger.info("inner1.info:" + v.foo));
    }

    /**
     * test Object if it has NULL field.
     */
    @Test
    public void testIfObjectHasNullField() {
        logger.info("----------------------testIfNull start----------------------\n");
        Outer outer = new Outer();
        Nested nested2 = new Nested();
        outer.nested = nested2;

        Optional<String> optStr = Optional.ofNullable(outer)
            .flatMap(o -> {
                Optional<Nested> nested = Optional.ofNullable(o.nested);
                logger.debug("o.nested: " + nested.orElse(null));
                return nested;
            })
            .flatMap(n -> {
                Optional<Inner> inner = Optional.ofNullable(n.inner);
                logger.debug("n.inner: " + inner.orElse(null));
                return inner;
            })
            .flatMap(inn -> {
                Optional<String> foo = Optional.ofNullable(inn.foo);
                logger.debug("inn.foo: " + foo.orElse(null));
                return foo;
            });

        assertFalse(optStr.isPresent());
        logger.info("----------------------testIfNull end----------------------\n");
    }

    /**
     * flatMap means map from Stream<A> to Stream<B>
     *
     * the flowing example is mapping Stream<String> to Stream<Bar>
     */
    private static void mapObject2() {

        // another loop way.
//        IntStream.range(0, 9).forEach(i -> {
//            logger.debug(i + "");
//        });

        Stream<String> stringStream = IntStream.range(1, 5).mapToObj(x -> x + "");

        Stream<Bar> barStream = stringStream.flatMap(t -> {
            List<Bar> bars = new ArrayList<>();
            for (int i = 0; i < Integer.parseInt(t); i++) {
                bars.add(new Bar("bar" + t + "_" + i));
            }
            return bars.stream();
        });

        barStream.forEach(bar -> {
            logger.debug(bar.name);
        });
    }

    private static void mapObject() {
        List<Foo> fooList = IntStream.range(0, 3)
            .mapToObj(i -> new Foo("foo" + i))
            .collect(Collectors.toList());

        fooList.forEach(foo -> {
            IntStream.range(0, 5)
                .forEach(i -> {
                    Bar bar = new Bar(foo.name + " -> bar" + i);
                    foo.bars.add(bar);
                });
        });

        // mapFlat: map fooList(3) into barList(15)
        /*
        We can use another simpler way.
         */
        /*
        Supplier<Stream<Bar>> supplierBars = () -> fooList.stream().flatMap(foo -> foo.bars.stream());

        //15
        logger.debug(supplierBars.get().count() + "");

        supplierBars.get().forEach(bar -> {
            logger.debug(bar.name);
        });
        */

        List<Bar> bars = fooList.stream().flatMap(foo -> foo.bars.stream()).collect(Collectors.toList());
        logger.debug("bars.size(): " + bars.size());
        logger.debug(bars.toString());
    }
}

class Outer {

    Nested nested;
}

class Nested {

    Inner inner;
}

class Inner {

    String foo;
}

class Foo {

    String name;
    List<Bar> bars = new ArrayList<>();

    Foo(String name) {
        this.name = name;
    }
}

class Bar {

    String name;

    Bar(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
