package edu.gqq.ayuan.week8;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * File Name: GraphDFSUsingTimeStamp.java
 *
 * @author Jagadeesh Vasudevamurthy
 * @year 2021
 */

class GraphDFSUsingTimeStamp {

    private Graph g;
    private int[] work;
    private boolean[] cycle;
    private ArrayList<Integer> topologicalOrderArray;
    private String f;
    //You can have any number of private classes, variables and functions

    GraphDFSUsingTimeStamp(Graph g, int[] work, boolean[] cycle, ArrayList<Integer> topologicalOrderArray,
        String f) {
        this.g = g;
        this.work = work;
        this.cycle = cycle;
        this.topologicalOrderArray = topologicalOrderArray;
        this.f = f;
        //You MUST WRITE 2 routines
        dfs();
        writeDFSDot();
    }

    private void writeDFSDot() {
    }

    private void dfs() {
    }

    /*
     * WRITE CODE BELOW
     * //YOU CAN HAVE ANY NUMBER OF PRIVATE VARIABLES, DATA STRUCTURES AND FUNCTIONS
     */

}
