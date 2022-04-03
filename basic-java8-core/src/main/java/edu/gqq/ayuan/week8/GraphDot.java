package edu.gqq.ayuan.week8;

import edu.gqq.ayuan.week8.GraphType.Type;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Map.Entry;

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
    public static final String END_BRACE = "}";
    public static final String DIRECT_GRAPH_LABLE = "edge [color=red]\n";
    public static final String UNDIRECT_GRAPH_LABLE = "edge [dir=none, color=red]\n";
    public static final String HEADER = "digraph g {\n";

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
        HashSet<Integer> visited = new HashSet<Integer>();
        try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fname)))) {
            out.write(HEADER);
            GraphType.Type t = g.type;
            boolean isUndirectedGraph = Type.UNDIRECTED.equals(t) || Type.WEIGHTED_UNDIRECTED.equals(t);
            boolean isWeightedGraph = Type.WEIGHTED_UNDIRECTED.equals(t) || Type.WEIGHTED_DIRECTED.equals(t);
            if (isUndirectedGraph) {
                out.write(UNDIRECT_GRAPH_LABLE);
            } else {
                out.write(DIRECT_GRAPH_LABLE);
            }

            for (Node node : g.nodes) {
                for (Entry<Integer, Edge> ety : node.fanout.entrySet()) {
                    if (isUndirectedGraph && visited.contains(ety.getKey())) {
                            continue;
                    }
                    String res = "";
                    if (isWeightedGraph) {
                        res = String.format("%s -> %s [lable = %s]",
                            g.io.getRealName(node.num),
                            g.io.getRealName(ety.getKey()),
                            ety.getValue().cost);
                    } else {
                        res = String.format("%s -> %s",
                            g.io.getRealName(node.num),
                            g.io.getRealName(ety.getKey()));
                    }
                    out.write(res);
                    out.newLine();
                }
                visited.add(node.num);
            }

            out.write(END_BRACE);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}