package edu.gqq.ayuan.week6;

import java.util.ArrayList;

/**
 * File Name: L0322.java
 *
 * @author
 * @year 2021
 */

/************************************************************
 * CUT AND PASTE ENTIRE FILE IN LEETCODE
 *************************************************************/
class Solution {

    //Nothing can be changed below
    //LEETCODE VERSION
    public int coinChange(int[] coins, int amount) {
        int[] work = {0};
        ArrayList<Integer> ans = new ArrayList<Integer>();
        boolean show = false;
        L0322 h = new L0322(coins, amount, ans, work, show);
        int numChange = ans.size();
        if (numChange == 1) {
            if (ans.get(0) == -1) {
                numChange = -1;
            }
        }
        return numChange;
    }
}

/************************************************************
 WRITE CLASS L0322
 *************************************************************/
class L0322 {

    private int[] d;
    private int amount;
    private ArrayList<Integer> ans;
    private int[] work;
    private boolean show;
    //You can have any number of private variables below

    L0322(int[] coins, int amount, ArrayList<Integer> ans, int[] work, boolean show) {
        //NOTHING CAN BE CHANGED HERE
        this.d = coins;
        this.amount = amount;
        this.ans = ans;
        this.work = work;
        this.show = show;
        //MUST WRITE 2 routines
        alg(); //1
        getSolution(); //2
        if (show) {
            System.out.println("WORK DONE = " + work[0]);
        }
    }

    private void incrementWork() {
        work[0]++;
    }

    /*
     * WRITE YOUR CODE HERE. YOU CAN HAVE ANY NUMBER
     * OF PRIVATE FUNCTIONS AND PRIVATE MEMBER FUNCTION
     */


    private void getSolution() {

    }

    private void alg() {

    }


    public static void main(String[] args) {
        System.out.println("Run L0322Test.java");
    }
}
