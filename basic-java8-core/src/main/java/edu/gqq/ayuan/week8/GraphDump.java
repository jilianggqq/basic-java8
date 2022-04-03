package edu.gqq.ayuan.week8;

import java.util.HashMap;
import java.util.stream.Collectors;

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
        int numV = g.numV();
        int numE = g.numE();
        System.out.println("GraphType." + g.type);
        System.out.println("NUM Vertices = " + numV);
        System.out.println("NUM Edges    = " + numE);
        for (Node node : g.nodes) {
            HashMap<Integer, Edge> fanoutMap = node.fanout;
            HashMap<Integer, Edge> faninMap = node.fanin;
            String strFanout = "NONE";
            String strFanin = "NONE";
            if (fanoutMap.size() > 0) {
                strFanout = fanoutMap.keySet().stream().map(x -> g.io.getRealName(x))
                    .collect(Collectors.joining(","));
            }
            if (faninMap.size() > 0) {
                strFanin = faninMap.keySet().stream().map(x -> g.io.getRealName(x))
                    .collect(Collectors.joining(","));
            }
            System.out.println(String.format("%s Fanouts: %s", g.io.getRealName(node.num), strFanout));
            System.out.println(String.format("%s FanIns: %s", g.io.getRealName(node.num), strFanin));
        }
    }
}