package edu.gqq.basic;

public class Scratch {

    // No compiling issue, but running issue. java.lang.ArithmeticException throws.
    public static void main(String[] args) {
        Object o = "s";
        if (o instanceof String) {
            while (1 / 0 == 0) {
            }
        } else {
            return;
        }
        System.out.println(o);
    }
}
