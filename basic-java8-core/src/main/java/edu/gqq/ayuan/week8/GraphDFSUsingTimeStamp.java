package edu.gqq.ayuan.week8;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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
    private HashMap<Integer, ArrayList<Integer>> node_timestamp;

    GraphDFSUsingTimeStamp(Graph g, int[] work, boolean[] cycle, ArrayList<Integer> topologicalOrderArray,
        String f) {
        this.g = g;
        this.work = work;
        this.cycle = cycle;
        this.topologicalOrderArray = topologicalOrderArray;
        this.f = f;
        //You MUST WRITE 2 routines
        dfs();
//        writeDFSDot();
    }



    /*
     * WRITE CODE BELOW
     * //YOU CAN HAVE ANY NUMBER OF PRIVATE VARIABLES, DATA STRUCTURES AND FUNCTIONS
     */

    private void dfs() {
        work[0] = 0;
        node_timestamp = new HashMap<>();
        for (Node n : g.nodes) {
            if (!node_timestamp.containsKey(n.num)) {
                helper(n, -1);
            }
        }
        Collections.reverse(topologicalOrderArray);
    }

    void helper(Node n, int last_visited) {
        if (node_timestamp.containsKey(n.num)) {
            if (node_timestamp.get(n.num).size() == 1) {
                cycle[0] = true;
            }
            return;
        }
        work[0]++;
        node_timestamp.put(n.num, new ArrayList<>(Arrays.asList(work[0])));
        for (Edge e : n.fanout.values()) {
            if (last_visited != e.other) {
                helper(g.getNode(e.other), n.num);
            }
        }
        work[0]++;
        node_timestamp.get(n.num).add(work[0]);
        topologicalOrderArray.add(n.num);
    }

    void writeDFSDot() {

        System.out.println("See dot file at " + f);
        try {
            FileWriter file = new FileWriter(f);
            file.write("digraph g {\n");
            file.write("  label = \"[");
            for (int i : topologicalOrderArray) {
                file.write(g.io.getRealName(i) + ' ');
            }
            file.write(String.format("] %s\"\n", cycle[0] ? "LOOP" : "NOLOOP"));
            for (Node n : g.nodes) {
                file.write(String.format("%s[label = <%s<BR /><FONT POINT-SIZE=\"10\">%d/%d</FONT>>]\n",
                    g.io.getRealName(n.num), g.io.getRealName(n.num),
                    node_timestamp.get(n.num).get(0), node_timestamp.get(n.num).get(1)));
            }
            file.write("edge [");
            if (g.type == GraphType.Type.UNDIRECTED || g.type == GraphType.Type.WEIGHTED_UNDIRECTED) {
                file.write("dir=none, ");
            }
            file.write("color=red]\n");

            for (Node n : g.nodes) {
                if (n.fanout.isEmpty()) {
                    continue;
                }
                String from = g.io.getRealName(n.num);
                for (Map.Entry elem : n.fanout.entrySet()) {
                    int to_n = (int) elem.getKey();
                    if (g.type == GraphType.Type.UNDIRECTED || g.type == GraphType.Type.WEIGHTED_UNDIRECTED) {
                        if (n.num > to_n) {
                            continue;
                        }
                    }
                    String to = g.io.getRealName(to_n);
                    file.write("  " + from + " -> " + to);
                    if (g.type == GraphType.Type.WEIGHTED_DIRECTED
                        || g.type == GraphType.Type.WEIGHTED_UNDIRECTED) {
                        file.write(String.format(" [label = %f]", ((Edge) elem.getValue()).cost));
                    }
                    file.write('\n');
                }
            }
            file.write("}\n");
            file.close();

        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
