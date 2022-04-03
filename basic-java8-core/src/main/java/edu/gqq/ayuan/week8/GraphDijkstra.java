package edu.gqq.ayuan.week8;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;


/**
 * File Name: GraphDijkstra.java Implements Dijkstra's algorithms
 *
 * @author Jagadeesh Vasudevamurthy
 * @year 2020
 */

/*
 * To compile you require: IntUtil.java RandomInt.java Graph.java GraphTest.javs GraphDijkstra.java
 */

class GraphDijkstra {

    //Note all fields are public
    public Graph g;
    public String title;
    public String startCity;
    public String dotFile; //Tree traversal picture as dot file
    public int[] work; //Note public
    public double[] cost; //Note Public. The space is already allocated. Fill -1 if path does not exist
    //YOU CANNOT ADD ANTHING HERE

    GraphDijkstra(Graph g, String title, String startCity, String f, int[] work, double[] cost) {
        //NOTHING CAN BE CHANGED BELOW
        this.g = g;
        this.title = title;
        this.startCity = startCity;
        this.dotFile = f;
        this.work = work;
        this.cost = cost;
        //You must implement DijkstraAlg as an object
        DijkstraAlg a = new DijkstraAlg(this);
    }
}
	
	/*--------------------------------------------------------------------------
	 *                        WRITE CODE BELOW
	 *          YOU CAN HAVE ANY NUMBER OF classes and private functions
	 ----------------------------------------------------------------------------*/


/*--------------------------------------------------------------------------
 *   Algorithm implementation as a class
 ----------------------------------------------------------------------------*/
class DijkstraAlg {

    private GraphDijkstra d;
    private int numberofNodeAddedToHeap;
    public final double INFINITY = Double.MAX_VALUE;
    //You can have any number of private classes and data structures


    DijkstraAlg(GraphDijkstra d) {
        this.d = d;
        numberofNodeAddedToHeap = 0;
        this.d.g.u.openDotFile(d.dotFile);
        alg();    //in alg use: d.g.u.appendDotFile(d.dotFile,t); //t is the string you are appending
        this.d.g.u.closeDotFile(d.dotFile);
    }

    private void updateWorkDone(int n) {
        d.work[0] = d.work[0] + n;
    }

    private void Statistics() {
        System.out.println(GraphType.gtype(d.g.type));
        System.out.println("Num Vertices = " + d.g.numV());
        System.out.println("Num Edges    = " + d.g.numE());
        System.out.println("Work done    = " + d.work[0]);
        System.out.println("numberofNodeAddedToHeap = " + numberofNodeAddedToHeap);
    }

    /*
     * Dijkstra algorithm
     * Time: O(V + E)
     * Space: O(V)
     */
    private void alg() {
        //WRITE CODE
    }

    public static void main(String[] args) {
        System.out.println("GraphDijkstra.java starts");
        System.out.println("Use GraphTest.java to test");
        System.out.println("GraphDijkstra.java Ends");
    }
}