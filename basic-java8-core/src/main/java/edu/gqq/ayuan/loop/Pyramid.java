package edu.gqq.ayuan.loop;

public class Pyramid {
    public static void main(String[] args) {
        printPyramid(6);
    }
    /**
     * print n rows Pyramid
     * @param n lines
     */
    public static void printPyramid(int n) {
        // int rowNum = 1;
        int spaceNum = n - 1;
        for(int rowNum = 1; rowNum <= n; ++rowNum) {
            for (int i = 0; i < spaceNum; i++) {
                System.out.print(" ");
            }
            for (int i = 0; i < 2 * rowNum - 1; i++) {
                System.out.print("*");
            }
            System.out.println();
            --spaceNum;
        }
    }
}


