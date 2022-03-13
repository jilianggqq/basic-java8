package edu.gqq.ayuan.week7;

import edu.gqq.ayuan.IntUtil;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayDeque;

/**
 * File Name: DequeTest.java
 *
 *
 * To Compile: IntUtil.java RandomInt.java Deque.java DequeTest.java
 *
 * @author Jagadeesh Vasudevamurthy
 * @year 2022
 */

/************************************************************
 NOTHING CAN BE CHANGED IN THIS FILE
 *************************************************************/
public class DequeTest {

    private boolean show = false;
    private IntUtil u = new IntUtil();

    DequeTest() {
        test();
    }

    private void print(ArrayDeque<Integer> g, Deque rg) {
        int gsize = g.size();
        int rsize = rg.size();
        if (gsize != rsize) {
            System.out.println("Gold has  " + gsize + " elements");
            System.out.println("You  have " + rsize + " elements");
            u.myassert(false);
        }
        if (show) {
            System.out.println("Gold SIZE: " + gsize + " Your Size : " + rsize);
        }

        Integer[] ga = new Integer[gsize];
        g.toArray(ga);
        for (int i = 0; i < gsize; ++i) {
            int gv = (int) ga[i];
            int rv = rg.getV(i);
            if (gv != rv) {
                System.out.println(" g[" + i + "] = " + gv);
                System.out.println("rg[" + i + "] = " + rv);
                u.myassert(false);
            }
            if (show) {
                System.out.print(" g[" + i + "] = " + gv + " ");
                System.out.println("rg[" + i + "] = " + rv);
            }
        }
    }

    @SuppressWarnings("removal")
    private void simple_test1() {
        show = true;
        ArrayDeque<Integer> g = new ArrayDeque();
        Deque rg = new Deque();

        g.addFirst(new Integer(1));
        rg.addFirst(new Integer(1));
        print(g, rg);

        g.addFirst(new Integer(2));
        rg.addFirst(new Integer(2));
        print(g, rg);

        g.addLast(new Integer(3));
        rg.addLast(new Integer(3));
        print(g, rg);

        for (int i = 3; i <= 7; ++i) {
            g.addFirst(new Integer(i));
            rg.addFirst(new Integer(i));
            print(g, rg);
        }

        for (int i = 8; i <= 9; ++i) {
            g.addLast(new Integer(i));
            rg.addLast(new Integer(i));
            print(g, rg);
        }

        for (int i = 0; i < 6; ++i) {
            g.removeLast();
            rg.removeLast();
            print(g, rg);
        }

        for (int i = 0; i < 3; ++i) {
            g.removeFirst();
            rg.removeFirst();
            print(g, rg);
        }

        print(g, rg);
        g.addLast(new Integer(8));
        rg.addLast(new Integer(8));
        g.addFirst(new Integer(4));
        rg.addFirst(new Integer(4));
        g.addLast(new Integer(9));
        rg.addLast(new Integer(9));
        g.addFirst(new Integer(9));
        rg.addFirst(new Integer(9));
        print(g, rg);
    }

    private void random_tests(int n) {
        System.out.println("Random Tests on " + n + " Starts");
        show = false;
        ArrayDeque<Integer> g = new ArrayDeque();
        Deque rg = new Deque();
        for (int i = 0; i < n; ++i) {
            print(g, rg); //to prove complexity through practice
            int x = i % 4;
            if (x == 0) {
                g.addFirst(new Integer(i));
                rg.addFirst(new Integer(i));
            } else if (x == 1) {
                g.addLast(new Integer(i));
                rg.addLast(new Integer(i));
            } else if (x == 2) {
                int gsize = g.size();
                int rsize = rg.size();
                if (gsize != rsize) {
                    System.out.println("RANDOM1: Gold has  " + gsize + " elements");
                    System.out.println("RANDOM1: You  have " + rsize + " elements");
                    u.myassert(false);
                }
                if (gsize != 0) {
                    g.removeLast();
                    rg.removeLast();
                }
            } else {
                int gsize = g.size();
                int rsize = rg.size();
                if (gsize != rsize) {
                    System.out.println("RANDOM2: Gold has  " + gsize + " elements");
                    System.out.println("RANDOM2: You  have " + rsize + " elements");
                    u.myassert(false);
                }
                if (gsize != 0) {
                    g.removeFirst();
                    rg.removeFirst();
                }
            }
        }
        System.out.println("Random Tests on " + n + " Ends");
    }

    private void test() {
        simple_test1();
        long startTime = System.nanoTime();
        for (int i = 10000; i <= 110000; i = i + 9999) {
            random_tests(i);
        }
        long endTime = System.nanoTime();
        double d = u.timeInSec(endTime, startTime);
        System.out.println("CPU Time = " + d + " secs");
    }

    public static void main(String[] args) {
        //Change path below
        String s = "C:\\Users\\jag\\OneDrive\\vasu\\work\\java11\\objects\\deque\\s\\expectedoutput.txt";
        if (false) { //Make it to true to write to a file
            System.out.println("Writing to file" + s);
            try {
                System.setOut(new PrintStream(new FileOutputStream(s)));
            } catch(FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("DequeTest STARTS");
        DequeTest m = new DequeTest();
        System.out.println("ALL PASSES. You got 100  ONLY if all methods are O(1)");
        System.out.println(
            "If you like this course rate me at: https://www.linkedin.com/in/jagadeesh-vasudevamurthy-6796591/");
        System.out.println("Attach output file " + s);
        System.out.println("Attach ONLY the Java file: Deque.java");
        System.out.println("No grade will be given if you attach screen shot as .jpg files");
        System.out.println("DequeTest ENDS");
    }
}