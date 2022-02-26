package edu.gqq.ayuan.week5; /**
 * File Name: Node2.java
 *
 * To Compile: IntUtil.java RandomInt.java Node2.java
 *
 * @author Jagadeesh Vasudevamurthy
 * @year 2021
 */

/************************************************************
 NOTHING CAN BE CHANGED IN THIS FILE
 *************************************************************/

public class Node2 {

    /* NOTHING CAN BE ADDED BELOW */
    public int d; //Many nodes can have same value
    public int t; //To test stable sort. Every mode will have unique value
    public Node2 next;

    Node2(int val, int unique) {
        d = val;
        t = unique;
        next = null;
    }


    public static void main(String[] args) {
        // NOTHING CAN BE CHANGED HERE
        System.out.println("Node2.java STARTS");
        String version = System.getProperty("java.version");
        System.out.println("Java version used for this program is " + version);
        System.out.println("Use Slist.java to  test the program");
        System.out.println("Node2.java ENDS");
    }
}