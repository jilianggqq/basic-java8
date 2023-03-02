package edu.gqq.algorithm;

public class ReverseArray {

    public static void main(String[] args) {
        reverse();
        reverse2();
    }

    private static void reverse2() {
        int[] arr = {3, 5, 23, 343, 2342, 2, 1};
        int len = arr.length;
        for (int i = 0; i < len / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[len - 1 - i];
            arr[len - 1 - i] = temp;
        }
        System.out.print("{");
        for (int ele : arr) {
            System.out.print(ele + ",");
        }
        System.out.println("}");
    }

    private static void reverse() {
        int[] arr = {3, 5, 23, 343, 2342, 2, 1};
        for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        System.out.print("{");
        for (int ele : arr) {
            System.out.print(ele + ",");
        }
        System.out.println("}");
    }
}
