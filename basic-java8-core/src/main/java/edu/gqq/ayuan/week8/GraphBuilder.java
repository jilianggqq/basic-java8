package edu.gqq.ayuan.week8;

import edu.gqq.ayuan.week8.GraphType.Type;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * File Name: GraphBuilder.java
 *
 * @author Jagadeesh Vasudevamurthy
 * @year 2021
 */

class GraphBuilder {

    private Graph g;
    //You can have any number of private variables

    GraphBuilder(Graph g, String f) {
        this.g = g;
        buildGraph(f);
    }

    private void buildGraph(String f) {
        System.out.println("Building Graph from file " + f);
        int numlines = 0;
        GraphType.Type t = g.type;
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            StringBuilder sb = new StringBuilder();
            String lastline = null;
            int notreadlines = 0;
            while (true) {
                String line1 = br.readLine();
                if (line1 == null) {
                    //System.out.println("lastline = " + lastline) ;
                    //System.out.println("notreadlines = " + notreadlines) ;
                    break;
                } else {
                    // 956468   9 0.00202841
                    //Eat both leading and trailing spaces using trim
                    String line = line1.trim();
                    //There may be more than one space
                    //956468   9 0.00202841
                    lastline = line;
                    String delims = "[ ]+";
                    String[] s = line.split(delims);
                    int l = s.length;
                    if (l == 3) {
                        ++numlines;
                        g.u.myassert((t == GraphType.Type.WEIGHTED_UNDIRECTED) || (t
                            == GraphType.Type.WEIGHTED_DIRECTED));
                    } else if (l == 2) {
                        ++numlines;
                        g.u.myassert((t == GraphType.Type.UNDIRECTED) || (t == GraphType.Type.DIRECTED));
                    } else {
                        //System.out.println("l = " + l + " line = " + line) ;
                        ++notreadlines;
                    }

                    /******************************************************************
                     WRITE YOUR CODE BELOW
                     ******************************************************************/
                    if (notreadlines >= 1 || s[0].equals(s[1])) {
                        continue;
                    }

                    int idxNode1 = g.io.insertOrFind(s[0], false);
                    int idxNode2 = g.io.insertOrFind(s[1], false);
                    double weight = l == 3 ? Double.parseDouble(s[2]) : 0;

                    Node node1 = g.buildNodeIfNotExistAndAppend(idxNode1);
                    Node node2 = g.buildNodeIfNotExistAndAppend(idxNode2);

                    // undirected.
                    if (GraphType.Type.UNDIRECTED.equals(t) || Type.WEIGHTED_UNDIRECTED.equals(t)) {
                        g.createEdge(node1, node2, weight, true);
                        g.createEdge(node2, node1, weight, true);
                        g.createEdge(node1, node2, weight, false);
                        g.createEdge(node2, node1, weight, false);
                    } else {
                        g.createEdge(node1, node2, weight, true);
                        g.createEdge(node2, node1, weight, false);
                    }
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}