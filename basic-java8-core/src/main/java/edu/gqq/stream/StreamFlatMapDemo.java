package edu.gqq.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.apache.log4j.Logger;

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
    private static final Logger logger = Logger.getLogger(StreamFlatMapDemo.class);

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
    }

    /**
     * flatMap means map from Stream<A> to Stream<B>
     *
     * the flowing example is mapping Stream<String> to Stream<Bar>
     */
    private static void mapObject2() {
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
        Supplier<Stream<Bar>> supplierBars = () -> fooList.stream().flatMap(foo -> foo.bars.stream());

        //15
        logger.debug(supplierBars.get().count());

        supplierBars.get().forEach(bar -> {
            logger.debug(bar.name);
        });

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
}
