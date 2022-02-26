package edu.gqq.ayuan.week5;

import edu.gqq.ayuan.IntUtil;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * File Name: Slist2.java
 *
 * @author Jagadeesh Vasudevamurthy
 * @year 2021
 */
/*
 * To compile you require: IntUtil.java RandomInt.java Slist2.java
 */

public class Slist2 {

    //You cannot add any variables to this class
    public Node2 first;
    private static final IntUtil u = new IntUtil();

    Slist2() {
        first = null;
    }

    /************************************************************
     NOTHING CAN BE CHANGED IN THIS FILE
     *************************************************************/

    /************************************************************
     Append n to the end of Slist2
     FILL
     TIME: O(1)
     Space: O(1)
     *************************************************************/
    public void append(Node2 n, Node2 last) {
        if (first == null && last == null) {
            first = n;
        } else {
            last.next = n;
        }
    }

    /************************************************************
     Return size of the Slist2
     FILL
     TIME: O(n)
     Space: O(1)
     *************************************************************/
    public int size() {
        int n = 0;
        Node2 s = first;
        while (s != null) {
            ++n;
            s = s.next;
        }
        return n;
    }


    /************************************************************
     Build Slist2 from Java Vanilla Array
     *************************************************************/
    public static Slist2 buildSlist(int[] a) {
        Slist2 l = new Slist2();
        int i = 0;
        Node2 last = null;
        for (int e : a) {
            Node2 n = new Node2(e, i++);
            l.append(n, last);
            last = n;
        }

        return l;
    }

    /************************************************************
     Print Slist2

     TIME: O(n)
     Space: O(1)
     *************************************************************/
    public void Pln(String t, Node2 n) {
        System.out.print(t);
        while (n != null) {
            System.out.print(n.d);
            if (n.next == null) {
                System.out.print("->NIL");
            } else {
                System.out.print("->");
            }
            n = n.next;
        }
        System.out.println();
    }

    public void Pln(String t) {
        Pln(t, first);
    }

    /************************************************************
     ASSERT Slist2 is ascending and stable
     0 1 2 is ok
     1 0 2 is not ok
     TIME: O(n)
     Space: O(1)
     *************************************************************/
    public void assertSlistInAscendingAndStable() {
        Node2 t = first;
        if (t != null) {
            Node2 prev = t;
            Node2 next = t.next;
            while (next != null) {
                if (next.d < prev.d) {
                    System.out.println("NOT IN ASCENDING " + next.d + " < " + prev.d);
                    u.myassert(false);
                }
                if (prev.d == next.d) {
                    //assures stable sort
                    if (prev.t >= next.t) {
                        System.out.println("UNSTABLE SORT " + prev.t + " < " + next.t);
                    }
                    u.myassert(prev.t < next.t);
                }
                prev = next;
                next = next.next;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Slist2 STARTS");

        System.out.println("Slist2 ENDS");
    }
}