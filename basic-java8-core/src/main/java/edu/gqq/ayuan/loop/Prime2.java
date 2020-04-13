package edu.gqq.ayuan.loop;

public class Prime2 {
    public static void main(String[] args) {
        System.out.println(2);
        for (int i = 3; i < 1000; i++) {
            int j = 2;
            boolean isPrime = true;
            while (j <= i - 1) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
                j++;
            }
            if (isPrime) {
                System.out.println(i);
            }
        }
    }

    // private static boolean isPrime(int num) {
    //     if (num == 2) return true;
    //     for (int i = 2; i < num; i++) {
    //         if (num % i == 0) return false;
    //     }
    //     return true;
    // }
}
