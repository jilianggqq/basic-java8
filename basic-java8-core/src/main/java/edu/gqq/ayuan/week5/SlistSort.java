package edu.gqq.ayuan.week5;

import edu.gqq.ayuan.IntUtil;

/**
 * File Name: SlistSort.java
 *
 * @author Jagadeesh Vasudevamurthy
 * @year 2021
 */
/*
 * To compile you require: IntUtil.java RandomInt.java Slist2.java SlistSort.java
 */

public class SlistSort {

    private Slist2 s;
    private int[] work;
    private boolean show;
    private static IntUtil u = new IntUtil();
    //You cannot add any variables to this class

    SlistSort(Slist2 s, String method, int[] work, boolean show) {
        this.s = s;
        this.work = work;
        this.show = show;
        if (method == "merge_sort") {
            merge_sort();
        } else if (method == "quick_sort") {
            quick_sort();
        }
    }

    private void increment_numCompare(int x) {
        work[0] = work[0] + x;
    }

    private void increment_numSwap(int x) {
        work[1] = work[1] + x;
    }

    private void increment_Recursion(int x) {
        work[2] = work[2] + x;
    }


    /************************************************************
     WRITE YOUR CODE BELOW
     YOU CAN HAVE ANY NUMBER OF PRIVATE METHODS AND PRIVATE CLASS
     *************************************************************/
    private void merge_sort() {
        if (show) {
            s.Pln("Before sort s = ");
        }
        //WRITE CODE BELOW
        //YOU CAN HAVE ANY NUMBER OF PRIVATE METHODS

        if (show) {
            s.Pln("After  sort s = ");
        }
    }

    private void quick_sort() {
        if (show) {
            s.Pln("Before sort s = ");
        }
        //WRITE CODE BELOW
        //YOU CAN HAVE ANY NUMBER OF PRIVATE METHODS

        if (show) {
            s.Pln("After  sort s = ");
        }
    }


    public static void main(String[] args) {
        System.out.println("SlistSort STARTS");

        System.out.println("SlistSort ENDS");
    }

}
	