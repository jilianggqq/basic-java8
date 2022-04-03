package edu.gqq.ayuan.week8;

import java.io.FileWriter;
import java.io.IOException;

/**
 * File Name: GraphDot.java
 *
 * @author Jagadeesh Vasudevamurthy
 * @year 2021
 */

class GraphDot {

    private Graph g;
    private String fname;
    //You can have any number of private variables

    GraphDot(Graph g, String n) {
        this.g = g;
        this.fname = n;
        dump();
    }

    /******************************************************************
     WRITE YOUR CODE BELOW
     ******************************************************************/
    private void dump() {
        System.out.println("See dot file at " + fname);

    }
}