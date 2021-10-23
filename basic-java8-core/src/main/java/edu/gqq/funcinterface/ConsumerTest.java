package edu.gqq.funcinterface;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsumerTest {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerTest.class);

    public static void main(String[] args) {
        // how to use consumer string
        Consumer<String> printConsumer = str -> System.out.println(str);
        printConsumer.accept("test str");//test str

        Stream<String> cities = Stream.of("Sydney", "Dhaka", "New York", "London");
        // why param in foreach is Consumer<? super T> action?
        // because in the foreach method, it must call action.accept(T type).
        // so, the Consumer action must be the superclass of T.
        cities.forEach(printConsumer);

        // Consumer is lambda expression. You can imagine that's a method.
        // this method can be called with params
        Consumer<Integer> integerConsumer = integer -> {
            System.out.println(integer + 3);
        };
        integerConsumer.accept(5);//8

        // 3. test getConsumer
        // 3.1 Integer
        Consumer<Integer> consumer = getConsumer("string1", 3);
        consumer.accept(5); // 5string13
        // 3.2 String
        getConsumer("second", "third").accept("first");
        // 3.3 object
        getConsumer(new Object(), new Object()).accept(new Object());

        // 4. test super
        // why param in exec is Consumer<? super T> action?
        // because in the exec method, it must call action.accept(T type).
        // so, the Consumer action must be the superclass of T.
        // If this Consumer is <? super T>, that means it can accept T type as its parameter.
        // 4.1 FileExec String
        Consumer<Object> printBookInfo = _identifier -> {
            if (_identifier instanceof String) {
                System.out.println("ISBN is " + _identifier);
            } else if (_identifier instanceof BigInteger) {
                System.out.println("book id is " + ((BigInteger) _identifier).intValue());
            } else {
                System.out.println("UNKNOWN type");
            }
        };

        System.out.println("---------------------- 4.1 FileExec String ----------------------");
        FileExec<String> bookIsbn = new FileExec<>();
        FileExec<BigInteger> bookId = new FileExec<>();
        bookIsbn._identifier = "BSK2342034234";
        bookIsbn.exec("Super hero", printBookInfo);

        System.out.println("---------------------- 4.2 FileExec BigInteger ----------------------");
        bookId._identifier = BigInteger.valueOf(134397934L);
        bookId.exec("Find the world", printBookInfo);

        // 5. test andThen
        testAndThen();
    }

    private static void testAndThen() {
        System.out.println("----------------------test andThen----------------------");

        List<String> cities = Arrays.asList("Sydney", "Dhaka", "New York", "London");
        Consumer<List<String>> upperCaseConsumer = list -> {
            // please find a stream way to change a list into Upper Case.
//            List<String> collect = list.stream().map(s -> s.toUpperCase()).collect(Collectors.toList());
//            System.out.println(list);
//            System.out.println(collect);
            list.replaceAll(x -> x.toUpperCase());

//            for (int i = 0; i < list.size(); i++) {
//                list.set(i, list.get(i).toUpperCase());
//            }
        };
        Consumer<List<String>> printConsumer = list -> list.stream().forEach(System.out::println);
        upperCaseConsumer.andThen(printConsumer).accept(cities);
    }

    public static <T> Consumer<T> getConsumer(Object obj, T val) {
//        T tObj = (T) obj;
        return t -> {
            System.out.printf("%s_%s_%s%n", t.toString(), obj.toString(), val.toString());
        };
    }
}

class FileExec<T> {

    private static final Logger logger = LoggerFactory.getLogger(FileExec.class);
    T _identifier;

    public void exec(String fileName, Consumer<? super T> action) {
        logger.info("fileName is " + fileName);
        action.accept(_identifier);
    }
}
