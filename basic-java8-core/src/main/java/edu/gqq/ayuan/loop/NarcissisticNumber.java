package edu.gqq.ayuan.loop;

public class NarcissisticNumber {
    public static void main(String[] args) {

        // primary code
        for (int i = 1; i < 10000; i++) {
            // if this number is Narcissistic Number, print it out
            if (isNarcissisticNumber(i)) {
                System.out.println(i);
            }
        }

        //test code
        // boolean res = isNarcissisticNumber(230);
        // System.out.println(res);
    }

    public static boolean isNarcissisticNumber(int num) {
        int sumOfCubes = 0;
        int backupNum = num;
        while (num > 0) {
            int firstDigital = num % 10;
            sumOfCubes += firstDigital * firstDigital * firstDigital;
            num /= 10;
        }
        return backupNum == sumOfCubes ? true : false;

        // equals to following
        // if (backupNum == sumOfCubes) {
        //     return true;
        // } else {
        //     return false;
        // }
    }
}
