package edu.gqq.algorithm.leetcode38;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * File Name: L0038Test.java
 *
 * @author Jagadeesh Vasudevamurthy
 * @year 2022
 */

/************************************************************
 NOTHING CAN BE CHANGED IN THIS FILE
 *************************************************************/
class L0038Test {

    private boolean show = true;
    private IntUtil u = new IntUtil();

    L0038Test() {
        testBed();
    }

    private void testBed() {
        tests();
        testn();
    }


    private void lookAndSay(String n, String e) {
        SolutionL0038 s = new SolutionL0038();
        String ans = s.countAndSay(n);
        if (show) {
            System.out.println("-----------------------------");
            System.out.println("orig = " + n);
            System.out.println("next = " + ans);
        }
        u.myassert(ans.equals(e));
    }

    private void lookAndSay(int n, String e) {
        SolutionL0038 s = new SolutionL0038();
        String ans = s.countAndSay(n);
        if (show) {
            System.out.println("-----------------------------");
            System.out.println("orig = " + n);
            System.out.println("mext = " + ans);
        }
        u.myassert(ans.equals(e));
    }

    private void tests() {
        {
            String n = "123";
            String e = "111213";
            lookAndSay(n, e);
        }
        {
            String n = "9999999999";
            String e = "109";
            lookAndSay(n, e);
        }
        {
            String n = "9876543210";
            String e = "19181716151413121110";
            lookAndSay(n, e);
        }

    }

    private void testn() {
        {
            int n = 1;
            String e = "1";
            lookAndSay(n, e);
        }
        {
            int n = 2;
            String e = "11";
            lookAndSay(n, e);
        }
        {
            int n = 3;
            String e = "21";
            lookAndSay(n, e);
        }
        {
            int n = 4;
            String e = "1211";
            lookAndSay(n, e);
        }
        {
            int n = 5;
            String e = "111221";
            lookAndSay(n, e);
        }
        for (int n = 1; n < 32; ++n) {
            SolutionL0038 s = new SolutionL0038();
            String ans = s.countAndSay(n);
            if (show) {
                System.out.println("n = " + n + " Length = " + ans.length());
                System.out.println("n = " + n + " " + ans);
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        //Change path below
        String s = "/Users/qiqiangguan/Downloads/L0038output.txt";
        if (true) { //Make it to true to write to a file
            System.out.println("Writing to file" + s);
            try {
                System.setOut(new PrintStream(new FileOutputStream(s)));
            } catch(FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("L0038 problem STARTS");
        L0038Test m = new L0038Test();
        System.out.println("Attach output file " + s);
        System.out.println("RUN NOW at: https://leetcode.com/problems/count-and-say/");
        System.out.println("L0038 problem ENDS");
    }
}

