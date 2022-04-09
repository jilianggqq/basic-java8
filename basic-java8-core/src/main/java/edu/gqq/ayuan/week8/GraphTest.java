package edu.gqq.ayuan.week8;

import edu.gqq.ayuan.IntUtil;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 * File Name: GraphTest.java
 *
 * @author Jagadeesh Vasudevamurthy
 * @year 2021
 */

class GraphTest {

    private IntUtil u = new IntUtil();

    GraphTest() {
        testBed();
    }

    private void dump(Graph g, String name) {
        System.out.println(GraphType.gtype(g.type));
        int numv = g.numV();
        int nume = g.numE();
        System.out.println("Num Vertices = " + numv);
        System.out.println("Num Edges    = " + nume);
    }

    private void build(String name, GraphType.Type graphType, int enodes, int eedges) {
        GraphIO io = new GraphIO();
        Graph g = new Graph(graphType, io);
        String f = GraphInputOutputDir.inputFileBase + name + ".txt";

        g.buildGraph(f);
        g.dumpGraph(name);
        f = GraphInputOutputDir.outputFileBase + name + ".dot";
        int v = g.numV();
        g.writeGraphAsDotFile(f);
        if (v < 25) {
            GraphInputOutputDir.dot2pdf(GraphInputOutputDir.outputFileBase + name);
        }
        if (v != enodes) {
            System.out.println("The graph has " + enodes + " Nodes. But you are telling " + v + " Nodes");
            u.myassert(v == enodes);
        }
        int e = g.numE();
        if (e != eedges) {
            System.out.println("The graph has " + eedges + " Edges. But you are telling " + e + " Edges");
            u.myassert(e == eedges);
        }
    }

    /*
     * Build graph, dump and print as a dotfile
     */
    private void testBuildGraph() {
        build("13", GraphType.Type.UNDIRECTED, 7, 24);
        build("14", GraphType.Type.WEIGHTED_UNDIRECTED, 6, 20);
        build("15", GraphType.Type.DIRECTED, 6, 6);
        build("16", GraphType.Type.WEIGHTED_DIRECTED, 5, 6);
        build("loopparallel", GraphType.Type.WEIGHTED_DIRECTED, 4, 3);
        build("cat", GraphType.Type.DIRECTED, 6, 7);
        build("hd2", GraphType.Type.WEIGHTED_DIRECTED, 78, 1095);
    }

    private void dfsUsingTimeStamp(String name, GraphType.Type graphType, boolean expectedHasloop) {
        GraphIO io = new GraphIO();
        Graph g = new Graph(graphType, io);
        String f = GraphInputOutputDir.inputFileBase + name + ".txt";

        System.out.println("---------------" + name + " ---------------------");
        g.buildGraph(f);
        //g.dumpGraph(name);
        f = GraphInputOutputDir.outputFileBase + name + ".dot";
        int v = g.numV();
        g.writeGraphAsDotFile(f);
        if (v < 25) {
            GraphInputOutputDir.dot2pdf(GraphInputOutputDir.outputFileBase + name);
        }
        int[] work = {0};
        boolean[] cycle = {false};
        ArrayList<Integer> topologicalOrderArray = new ArrayList<Integer>();
        f = GraphInputOutputDir.outputFileBase + name + "dfs.dot";
        System.out.println("-----Running dfsUsingTimeStamp on " + name + "-----");
        g.dfsUsingTimeStamp(work, cycle, topologicalOrderArray, f);
        int size = topologicalOrderArray.size();
        if (size != v) {
            System.out.println("The graph has " + v + " Nodes. But you visited " + size + " Nodes");
            u.myassert(false);
        }
        if (cycle[0] != expectedHasloop) {
            if (expectedHasloop == false) {
                System.out.println("The graph has NO loop, But you are telling graph has loop");
            } else {
                System.out.println("The graph has loop, But you are telling graph has NO loop");
            }
            u.myassert(cycle[0] == expectedHasloop);
        }
        boolean x = g.assertDFS(cycle[0], topologicalOrderArray);
        if (x == false) {
            System.out.println("Toplogical ordering is wrong");
            u.myassert(false);
        }
        dump(g, name);
        System.out.println("Work Done    = " + work[0]);
        System.out.print("Has Loop    = ");
        if (cycle[0]) {
            System.out.println(" YES");
        } else {
            System.out.println(" NO");
        }
        System.out.print("Topological order = ");
        for (int i : topologicalOrderArray) {
            System.out.print(g.io.getRealName(i) + " ");
        }
        System.out.println();
        System.out.println("You can see DFS traversal dot file at " + f);
        if (v < 25) {
            GraphInputOutputDir.dot2pdf(GraphInputOutputDir.outputFileBase + name + "dfs");
        } else {
            System.out.println("Too big graph to make pdf file from dot file using Graphviz");
        }
    }

    /*
     * Test dfsUsingTimeStamp
     */
    private void testDfsUsingTimeStamp() {
        dfsUsingTimeStamp("u1", GraphType.Type.UNDIRECTED, false);
        dfsUsingTimeStamp("1", GraphType.Type.UNDIRECTED, false);
        dfsUsingTimeStamp("udf1", GraphType.Type.UNDIRECTED, true);//loop
        dfsUsingTimeStamp("2", GraphType.Type.DIRECTED, false);
        dfsUsingTimeStamp("3", GraphType.Type.DIRECTED, true); //loop
        dfsUsingTimeStamp("cat", GraphType.Type.DIRECTED, false);
        dfsUsingTimeStamp("7", GraphType.Type.WEIGHTED_DIRECTED, false);
//        dfsUsingTimeStamp("mediumEWD", GraphType.Type.WEIGHTED_DIRECTED, true); //loop
    }

    private void testDijkstra() {
        {
            String[] n = {"7", "loopparallel", "17", "hd1", "hd2", "hd3"};
            GraphType.Type[] t = {GraphType.Type.WEIGHTED_DIRECTED, GraphType.Type.WEIGHTED_UNDIRECTED,
                GraphType.Type.WEIGHTED_UNDIRECTED, GraphType.Type.WEIGHTED_UNDIRECTED,
                GraphType.Type.WEIGHTED_UNDIRECTED, GraphType.Type.WEIGHTED_UNDIRECTED};
            String[] s = {"0", "s", "A", "17", "60", "85"}; //starting city

            double[] w0 = {0.0, 5.0, 3.0, 9.0, 13.0, 8.0, 7.0};
            double[] w1 = {0.0, 1.0, 6.0, 7.0};
            double[] w2 = {2.0, 7.0, 5.0, 1.0, 3.0, 7.0, 0.0};
            double[] w3 = {20.0, 22.0, 25.0, 27.0, 25.0, 68.0, 86.0, 39.0, 70.0, 36.0, 53.0, 91.0, 35.0, 88.0,
                30.0, 43.0, 0.0, 54.0, 74.0, 41.0};
            double[] w4 = {9.0, 13.0, 8.0, 10.0, 8.0, 5.0, 8.0, 5.0, 12.0, 1.0, 7.0, 15.0, 4.0, 8.0, 9.0, 4.0,
                11.0, 1.0, 4.0, 12.0, 9.0, 11.0, 7.0, 9.0, 10.0, 9.0, 7.0, 10.0, 5.0, 10.0, 11.0, 9.0, 1.0,
                7.0, 12.0, 6.0, 12.0, 15.0, 10.0, 11.0, 15.0, 6.0, 10.0, 7.0, 9.0, 7.0, 7.0, 14.0, 5.0, 13.0,
                8.0, 8.0, 10.0, 7.0, 4.0, 6.0, 3.0, 8.0, 11.0, 11.0, 12.0, 4.0, 9.0, 9.0, 7.0, 7.0, 7.0, 0.0,
                13.0, 6.0, 7.0, 8.0, 8.0, 3.0, 5.0, 6.0, 11.0, 5.0};
            double[] w5 = {154.0, 98.0, 90.0, 49.0, 186.0, 190.0, 178.0, 114.0, 123.0, -1.0, -1.0, -1.0,
                123.0, -1.0, 104.0, -1.0, -1.0, -1.0, 207.0, 134.0, 123.0, 75.0, 155.0, -1.0, 198.0, 68.0,
                90.0, 170.0, 135.0, -1.0, 103.0, 145.0, -1.0, 54.0, 111.0, 163.0, 173.0, 115.0, 87.0, 159.0,
                -1.0, 94.0, 102.0, -1.0, 76.0, 67.0, 167.0, 138.0, 216.0, -1.0, 172.0, 102.0, 212.0, 163.0,
                103.0, 112.0, -1.0, 182.0, 145.0, 92.0, -1.0, -1.0, 194.0, -1.0, 182.0, -1.0, 201.0, 96.0,
                -1.0, 85.0, 121.0, 108.0, 161.0, 130.0, 100.0, 120.0, -1.0, 118.0, 215.0, 92.0, 156.0, 162.0,
                163.0, 168.0, 0.0, 71.0, 110.0, -1.0, -1.0, 190.0, 217.0, 100.0, 105.0, 178.0};
            double[][] w = {w0, w1, w2, w3, w4, w5};

            int nl = n.length;
            int tl = t.length;
            int sl = s.length;
            int dl = w.length;

            u.myassert(nl == tl);
            u.myassert(nl == sl);
            u.myassert(nl == dl);

            for (int i = 0; i < nl; ++i) {
                String name = n[i];
                GraphType.Type type = t[i];
                GraphIO io = new GraphIO();
                Graph g = new Graph(type, io);
                String f = GraphInputOutputDir.inputFileBase + name + ".txt";
                System.out.println("---------------" + name + " ---------------------");
                g.buildGraph(f);
                //g.dumpGraph(name);
                f = GraphInputOutputDir.outputFileBase + name + ".dot";
                int v = g.numV();
                g.writeGraphAsDotFile(f);
                GraphInputOutputDir.dot2pdf(GraphInputOutputDir.outputFileBase + name);

                int[] work = {0};
                double[] cost = new double[v];//cost from start city to all other city.If not reachable -1
                f = GraphInputOutputDir.outputFileBase + name + "traversal.dot";
                g.dijkstra(name, s[i], f, work, cost);
                GraphInputOutputDir.dot2pdf(GraphInputOutputDir.outputFileBase + name + "traversal");
                System.out.println("The shortest costs are as follows");
                for (int j = 0; j < v; ++j) {
                    System.out.print(cost[j]);
                    if (j < v - 1) {
                        System.out.print(",");
                    }
                }
                System.out.println();
                //assert your cost is right
                for (int j = 0; j < v; ++j) {
                    if (cost[j] != w[i][j]) {
                        System.out.print(
                            "YOUR SHORTEST PATH IS COST = " + cost[j] + " The correct cost is " + w[i][j]);
                        u.myassert(false);
                    }
                }
            }
        }
    }

    private void testBed() {
//        testBuildGraph();
        testDfsUsingTimeStamp() ;
        //testDijkstra();
    }

    public static void main(String[] args) {
        String s = GraphInputOutputDir.outputFileBase + "output.txt";
        if (true) { //Make it to true to write to a file
            System.out.println("Writing to " + s);
            try {
                System.setOut(new PrintStream(new FileOutputStream(s)));
            } catch(FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        String version = System.getProperty("java.version");
        System.out.println("Java version used for this program is " + version);
        System.out.println("GraphTest.java starts");
        GraphTest g = new GraphTest();
        System.out.println("GraphTest.java Ends");
    }
}
	