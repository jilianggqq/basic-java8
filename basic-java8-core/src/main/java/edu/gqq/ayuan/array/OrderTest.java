package edu.gqq.ayuan.array;

public class OrderTest {
    public static void main(String[] args) {
        int[] a1 = {1, 3, 5, 7, 19, 26};
        int[] a2 = {2, 4, 6, 8, 10};
        int[] a = new int[a1.length + a2.length];
        int pForA1 = 0;
        int pForA2 = 0;
        for (int i = 0; i < a.length; i++) {
            if (pForA1 >= a1.length) {
                a[i] = a2[pForA2];
                pForA2++;
            } else if (pForA2 >= a2.length) {
                a[i] = a1[pForA1];
                pForA1++;
            } else if (a1[pForA1] <= a2[pForA2]) {
                a[i] = a1[pForA1];
                pForA1++;
            } else {
                a[i] = a2[pForA2];
                pForA2++;
            }
        }

        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }
}
