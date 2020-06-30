package edu.gqq.ayuan.loop;

public class NumberReverse {
    public static void main(String[] args) {
        int num = 12345;
        String res = "";
        while (num > 0) {
            res += num % 10;
            num /= 10;
        }
        System.out.println(res);
    }

    public static boolean isGood(int num) {
        if (num > 3) {
            return true;
        }
        return false;
    }
}
