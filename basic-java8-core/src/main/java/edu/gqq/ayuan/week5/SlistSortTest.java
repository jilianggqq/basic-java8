package edu.gqq.ayuan.week5;

import edu.gqq.ayuan.IntUtil;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * File Name: SlistSortTest.java
 *
 *
 * To Compile: IntUtil.java RandomInt.java SlistSort.java SlistSortTest.java
 *
 * @author Jagadeesh Vasudevamurthy
 * @year 2021
 */

/************************************************************
 NOTHING CAN BE CHANGED IN THIS FILE
 *************************************************************/
public class SlistSortTest {

    private boolean show = true;
    private IntUtil u = new IntUtil();

    SlistSortTest() {
        testBasic("merge_sort");
//        testBasic("quick_sort");
        testRandom("merge_sort");
//        testRandom("quick_sort");
        testAllreadySorted("merge_sort");
//        testAllreadySorted("quick_sort");
    }

    private void sort1(Slist2 s, String method) {
        if (show) {
            System.out.println("********* " + method + " START ********** ");
        }
        int[] work = {0, 0, 0};
        int os = s.size();
        SlistSort o = new SlistSort(s, method, work, show);
        int ns = s.size();
        if (os != ns) {
            System.out.println("list size before sorting: " + os);
            System.out.println("list size after  sorting: " + ns);
            System.out.println("You will get ZERO points");
        }
        u.myassert(os == ns);
        s.assertSlistInAscendingAndStable();
        u.printStatistics(os, work[0], work[1], work[2]);
        if (show) {
            System.out.println("********* " + method + " ENDS********** ");
        }
    }

    private void testBasic(String method) {
        show = true;
        int b[][] = u.testArray();
        int l = b.length;
        for (int i = 0; i < l; ++i) {
            int[] a = b[i];
            Slist2 s = Slist2.buildSlist(a);
            sort1(s, method);
        }

    }

    private void testRandom(String method) {
        show = false;
        int b[][] = u.testArray();
        int l = b.length;
        boolean t = false;
        int j = 0;
        for (int i = 5000; i < 25000; i = i + 5000) {
            if (t == false) {
                j = i - 1; //odd
                t = true;
            } else {
                j = i; //even
                t = false;
            }
            int[] a = u.generateRandomNumber(j, false, 0,
                j / 10); //So that there will be negatives and duplicates
            System.out.println(" ============================================= ");
            System.out.println("Running  " + method + " on " + j + " Random numbers");
            Slist2 s = Slist2.buildSlist(a);
            sort1(s, method);
            System.out.println(" ============================================= ");
        }
    }

    private void testAllreadySorted(String method) {
        show = false;
        int b[][] = u.testArray();
        int l = b.length;
        boolean t = false;
        int j = 0;
        for (int i = 5000; i < 25000; i = i + 5000) {
            if (t == false) {
                j = i - 1; //odd
                t = true;
                int[] a = u.generateNumberInIncreasingOrder(j,
                    1); //2 sequences of sorted so diplicates will be there
                System.out.println(" ============================================= ");
                System.out.println(
                    "Running  " + method + " on " + a.length + " Already increasing order with duplicates");
                Slist2 s = Slist2.buildSlist(a);
                sort1(s, method);
                System.out.println(" ============================================= ");
            } else {
                j = i; //even
                t = false;
                int[] a = u.generateNumberInDescendingOrder(j, 1);
                System.out.println(" ============================================= ");
                System.out.println("Running  " + method + " on " + a.length + " Already Decreasing order");
                Slist2 s = Slist2.buildSlist(a);
                sort1(s, method);
                System.out.println(" ============================================= ");
            }

        }
    }

    public static void main(String[] args) {
        //Change path below
        String s = "C:\\Users\\jag\\OneDrive\\vasu\\work\\java11\\objects\\SlistSort\\s\\expectedoutput.txt";
        if (false) { //Make it to true to write to a file
            System.out.println("Writing to file" + s);
            try {
                System.setOut(new PrintStream(new FileOutputStream(s)));
            } catch(FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("SlistSortTest STARTS");
        SlistSortTest m = new SlistSortTest();
        System.out.println("MUST PRINT ONLY WHEN show = true");
        System.out.println("Attach output file " + s);
        System.out.println("Attach ONLY the Java file SlistSort.java");
        System.out.println("No grade will be given if you attach screen shot as .jpg files");
        System.out.println("SlistSortTest ENDS");
    }
}