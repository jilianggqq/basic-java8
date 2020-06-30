package edu.gqq.ayuan.loop;

public class Bricks {
    public static void main(String[] args) {
        int i = 0;
        int j = 36;
        while (i <= 36) {
            j = 36 - i;
            if (i % 3 == 0) {
                int a = i / 3;
                int b = 3 * j;
                if (a + b == 36) {
                    break;
                }
            }
            ++i;
        }
        System.out.println(i);
        System.out.println(j);
    }
}
