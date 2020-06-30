package edu.gqq.lambda;

import java.util.function.Consumer;

public class ConsumerTest {

    public static void main(String[] args) {

        // how to use consumer string
        Consumer<String> stringConsumer = str -> System.out.println(str);

        // Consumer is lambda expression. You can imagine that's a method.
        // this method can be called with params
        Consumer<Integer> integerConsumer = integer -> {
            System.out.println(integer + 3);
        };

        stringConsumer.accept("test str");//test str
        integerConsumer.accept(5);//8

        // 3. test getConsumer
        Consumer<Integer> consumer = getConsumer("string1", 3);
        consumer.accept(5); // 5string13

    }

    public static <T> Consumer<T> getConsumer(Object obj, T val) {
//        T tObj = (T) obj;
        return t -> {
            System.out.println(t.toString() + obj.toString() + val.toString());
        };
    }
}
