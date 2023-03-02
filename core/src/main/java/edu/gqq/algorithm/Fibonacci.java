package edu.gqq.algorithm;

public class Fibonacci {

    public static void main(String[] args) {
        // output the result of Fibonacci. 1 -> 1, 2 -> 1, 3 -> 2, 4->3, 5->5, 6->8, 7-> 13 ...
        int n = 10;
        int fibo = getFiboByN(n);
        System.out.println(String.format("fibo(%d) is %d", n, fibo));
    }

    private static int getFiboByN(int n) {
        int x = 1, y = 1;
        for (int i = 3; i <= n; i++) {
            int temp = x + y;
            x = y;
            y = temp;
        }
        return y;
    }
}
