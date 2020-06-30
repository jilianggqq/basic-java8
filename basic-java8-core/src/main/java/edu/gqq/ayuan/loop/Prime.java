package edu.gqq.ayuan.loop;

public class Prime {
    public static void main(String[] args) {
        for (int i = 2; i < 1000; i++) {
            if (isPrime(i)) {
                System.out.println(i);
            }
        }
    }

    private static boolean isPrime(int num) {
        if (num == 2) return true;
        for (int i = 2; i < num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
