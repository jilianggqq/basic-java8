package edu.gqq.ayuan.week6;

import edu.gqq.ayuan.IntUtil;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * File Name: L0322Test.java
 *
 * @author Jagadeesh Vasudevamurthy
 * @year 2021
 */

/************************************************************
 NOTHING CAN BE CHANGED IN THIS FILE
 *************************************************************/
class L0322Test {

    private boolean show = true;
    private IntUtil u = new IntUtil();

    L0322Test() {
        testBed();
    }

    private void computeChange(int[] coins, int amount, int expectedAns) {
        int[] work = {0};
        ArrayList<Integer> ans = new ArrayList<Integer>();
        L0322 h = new L0322(coins, amount, ans, work, show);
        int numChange = ans.size();
        if (numChange == 1) {
            if (ans.get(0) == -1) {
                numChange = -1;
            }
        }
        if (numChange != expectedAns) {
            System.out.println("Expected answer is " + expectedAns + ". But your answer is " + numChange);
            u.myassert(false);
        }
        if (numChange != -1) {
            int s = 0;
            for (int e : ans) {
                s = s + e;
            }
            if (s != amount) {
                System.out.println(
                    "You need to give me is " + amount + ". But you gave me " + s + ". I will call cop");
                u.myassert(false);
            }
        }
        if (amount > 0) {
            if (work[0] == 0) {
                System.out.println("How did you solve the problem with 0 work?");
                u.myassert(false);
            }
        }
    }

    private void testBed() {
        show = true;
        {
            System.out.println("------------test1---------------------");
            int[] w = {1, 3, 4};
            computeChange(w, 6, 2);
        }
        {
            System.out.println("------------test2---------------------");
            int[] w = {1, 2, 6, 10, 24, 30, 90};
            computeChange(w, 100, 2);
        }
        {
            System.out.println("------------Amazon stamp--------------------");
            int[] w = {1, 2, 6, 10, 24, 30, 90};
            computeChange(w, 34, 2);
        }
        {
            System.out.println("------------US coins--------------------");
            int[] w = {1, 5, 10, 25};
            computeChange(w, 25, 1);
        }
        {
            System.out.println("------------Weird Coins--------------------");
            int[] w = {1, 5, 10, 12};
            computeChange(w, 16, 3);
        }
        {
            System.out.println("------------ZERO change--------------------");
            int[] w = {1};
            computeChange(w, 0, 0);
        }
        {
            System.out.println("------------ONE change--------------------");
            int[] w = {1};
            computeChange(w, 1, 1);
        }
        {
            System.out.println("------------TWO change--------------------");
            int[] w = {1};
            computeChange(w, 2, 2);
        }
        {
            System.out.println("------------Cannot give change--------------------");
            int[] w = {2};
            computeChange(w, 3, -1);
        }
        {
            show = false;
            System.out.println("------------can give change--------------------");
            int[] w = {186, 419, 83, 408};
            computeChange(w, 6249, 20);
        }
        {
            show = false;
            System.out.println("------------can give change--------------------");
            int[] w = {474, 83, 404, 3};
            computeChange(w, 264, 8);
        }
    }

    public static void main(String[] args) {
        //Change path below
        String s = "C:\\Users\\jag\\OneDrive\\vasu\\work\\leetcode\\L0322Coinchage\\java11\\L0322output.txt";
        if (false) { //Make it to true to write to a file
            System.out.println("Writing to file" + s);
            try {
                System.setOut(new PrintStream(new FileOutputStream(s)));
            } catch(FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("L0322 problem STARTS");
        L0322Test m = new L0322Test();
        System.out.println("Attach output file " + s);
        System.out.println("RUN NOW at: https://leetcode.com/problems/coin-change/");
        System.out.println("L0322 problem ENDS");
    }
}
