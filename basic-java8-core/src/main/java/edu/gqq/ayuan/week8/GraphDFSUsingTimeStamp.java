package edu.gqq.ayuan.week8;

import java.io.FileWriter;
import java.io.IOException;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.collections4.MapUtils;

/**
 * File Name: GraphDFSUsingTimeStamp.java
 *
 * @author Jagadeesh Vasudevamurthy
 * @year 2021
 */

class GraphDFSUsingTimeStamp {

    public static final String FOOTER = "}\n";
    private Graph g;
    private int[] work;
    private boolean[] cycle;
    private ArrayList<Integer> topologicalOrderArray;
    private String f;
    //You can have any number of private classes, variables and functions
    // SimpleEntry is a key value pair in Java8
    private final HashMap<Integer, SimpleEntry<Integer, Integer>> mapTimeStamps = new HashMap<>();
    private final HashSet<Integer> cycleSet = new HashSet<>();

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



    /*
     * WRITE CODE BELOW
     * //YOU CAN HAVE ANY NUMBER OF PRIVATE VARIABLES, DATA STRUCTURES AND FUNCTIONS
     */

    private void dfs() {
        work[0] = 0;
        for (Node n : g.nodes) {
            if (!mapTimeStamps.containsKey(n.num)) {
                helper(n, -1);
            }
        }
        Collections.reverse(topologicalOrderArray);
    }

    private void helper(Node n, int lastVisited) {
        if (cycleSet.contains(n.num)) {
            cycle[0] = true;
        }
        if (mapTimeStamps.containsKey(n.num)) {
            return;
        }
        work[0]++;
        mapTimeStamps.put(n.num, new SimpleEntry(work[0], -1));
        cycleSet.add(n.num);
        for (Edge e : n.fanout.values()) {
            if (lastVisited != g.getNode(e.other).num) {
                helper(g.getNode(e.other), n.num);
            }
        }
        cycleSet.remove(n.num);
        work[0]++;
        mapTimeStamps.get(n.num).setValue(work[0]);
        topologicalOrderArray.add(n.num);
    }

    void writeDFSDot() {

        System.out.println("See dot file at " + f);
        try {
            FileWriter file = new FileWriter(f);
            // 1. header
            String header = String.format("digraph g {\n label = \"[%s] %s\"\n",
                topologicalOrderArray.stream().map(x -> g.io.getRealName(x)).collect(Collectors.joining(" ")),
                cycle[0] ? "LOOP" : "NOLOOP");
            file.write(header);

            // 2. labels
            List<String> labels = g.nodes.stream().map(n -> {
                return String.format("%s[label = <%s<BR /><FONT POINT-SIZE=\"10\">%d/%d</FONT>>]\n",
                    g.io.getRealName(n.num), g.io.getRealName(n.num),
                    mapTimeStamps.get(n.num).getKey(), mapTimeStamps.get(n.num).getValue());
            }).collect(Collectors.toList());
            for (String label : labels) {
                file.write(label);
            }

            // 3 body
            String dir = isUndirectedGraph(g) ? "dir=none, " : "";
            String bodyHeader = String.format("edge [%scolor=red]\n", dir);
            file.write(bodyHeader);
            for (Node n : g.nodes) {
                if (MapUtils.isNotEmpty(n.fanout)) {
                    String from = g.io.getRealName(n.num);
                    for (int toNode : n.fanout.keySet()) {
                        if (isUndirectedGraph(g) && n.num > toNode) {
                            continue;
                        }
                        String to = g.io.getRealName(toNode);
                        file.write("  " + from + " -> " + to);
                        if (isWeightedGraph(g)) {
                            file.write(String.format(" [label = %f]", n.fanout.get(toNode).cost));
                        }
                        file.write('\n');
                    }
                }
            }

            // 4. footer
            file.write(FOOTER);
            file.close();

        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private boolean isWeightedGraph(Graph g) {
        return g.type == GraphType.Type.WEIGHTED_DIRECTED
            || g.type == GraphType.Type.WEIGHTED_UNDIRECTED;
    }

    private boolean isUndirectedGraph(Graph g) {
        return g.type == GraphType.Type.UNDIRECTED || g.type == GraphType.Type.WEIGHTED_UNDIRECTED;
    }
}
