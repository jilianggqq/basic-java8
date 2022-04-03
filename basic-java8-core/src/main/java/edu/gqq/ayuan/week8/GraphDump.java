package edu.gqq.ayuan.week8;

import java.util.HashMap;

/**
 * File Name: GraphDump.java
 *
 * @author Jagadeesh Vasudevamurthy
 * @year 2021
 */

class GraphDump {

    private Graph g;
    private String name;
    //You can have any number of private variables

    GraphDump(Graph g, String n) {
        this.g = g;
        this.name = n;
        dump();
    }

    /******************************************************************
     WRITE YOUR CODE BELOW
     ******************************************************************/
    private void dump() {
        System.out.println("------------ " + name + " ------------");

    }


}