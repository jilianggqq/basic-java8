package edu.gqq.ayuan.loop;

public class ByCharts {
    public static void main(String[] args) {
        // for (int i = 1; i <= 9; i++) {
        //     for (int j = 1; j <= i; j++) {
        //         System.out.print(i + "X" + j + "=" + (i * j) + "\t");
        //     }
        //     System.out.println();
        // }

        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                if (j > i) continue;
                System.out.print(i + "X" + j + "=" + (i * j) + "\t");
            }
            System.out.println();
            // System.out.println();
        }
    }
}
