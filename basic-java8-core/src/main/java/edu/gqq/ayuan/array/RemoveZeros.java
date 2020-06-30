package edu.gqq.ayuan.array;

public class RemoveZeros {
    public static void main(String[] args) {
        int[] arr = {2, 3, 0, 0, 34, 45, 0, 2, 7, 5};
        int zeroNum = findZeroNums(arr);
        int[] res = new int[arr.length - zeroNum];
        int idx = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                res[idx++] = arr[i];
            }
        }
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }
    }

    private static int findZeroNums(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int num = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) num++;
        }
        return num;
    }


}
