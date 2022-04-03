package edu.gqq.ayuan.week8;

import edu.gqq.ayuan.IntUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * File Name: Graph.java
 *
 * @author Jagadeesh Vasudevamurthy
 * @year 2021
 */


/*********************************************************************
 Nothing can be changed below
 **********************************************************************/
class Edge {

    public int other;
    public double cost;

    Edge(int other, double cost) {
        this.other = other;
        this.cost = cost;
    }
}

/*********************************************************************
 class Node
 NOTHING CAN BE CHANGED
 **********************************************************************/
class Node {

    public int num;
    public HashMap<Integer, Edge> fanout; // Key is int. Value is Edge
    public HashMap<Integer, Edge> fanin; // Key is int. Value is Edge

    Node(int n) {
        this.num = n;
        fanout = new HashMap<Integer, Edge>();
        fanin = new HashMap<Integer, Edge>();
    }

    // Does this node has a fanout of 'n'
    // Time: THETA(1) Space; THETA(1)
    Edge hasAFanoutEdge(int n) {
        // Key is int. Value is Edge
        Edge stored = fanout.get(n); //O(1)
        return stored; //NULL if not there
    }

    //Does this node has a fanin of 'n'
    // Time: THETA(1) Space; THETA(1)
    Edge hasAFaninEdge(int n) {
        // Key is int. Value is Edge
        Edge stored = fanin.get(n); //O(1)
        return stored; //NULL if not there
    }
}

/*********************************************************************
 class Graph
 NOTHING CAN BE CHANGED
 **********************************************************************/
class Graph {

    public GraphType.Type type; //Type of the graph
    public GraphIO io; //input/output
    public ArrayList<Node> nodes; //Array of all nodes
    public int numEdges;
    public IntUtil u = new IntUtil();

    Graph(GraphType.Type type, GraphIO io) {
        this.type = type;
        this.io = io;
        nodes = new ArrayList<Node>();
        numEdges = 0;
    }

    public int numV() {
        return nodes.size();
    }

    public int numE() {
        return numEdges;
    }

    public Node getNode(int n) {
        u.myassert(n >= 0 && n < numV());
        return nodes.get(n);
    }

    /*****************************************************************
     TIME: THETA(1)
     SPACE: THETA(1)
     *****************************************************************/
    public Node buildNodeIfNotExistAndAppend(int no) {
        u.myassert(no >= 0);
        int l = nodes.size();
        if (no < l) {
            return nodes.get(no);
        }
        Node n = new Node(no);
        nodes.add(n);
        return n;
    }

    /*****************************************************************
     TIME: THETA(1)
     SPACE: THETA(1)
     *****************************************************************/
    public void createEdge(Node from, Node to, double w, boolean fanout) {
        Edge e = new Edge(to.num, w);
        if (fanout == true) {
            // SEE e is already there in fanouts of nodes 'from'
            Edge stored = from.hasAFanoutEdge(to.num);
            if (stored == null) {
                //First time
                u.myassert(e.other == to.num);
                from.fanout.put(to.num, e);
                ++numEdges;
            } else {
                //Already there. Store the cheapest cost
                double v = stored.cost;
                if (w < v) {
                    stored.cost = w; //Reduced to low cost
                }
            }
        } else {
            // SEE e is already there in fanin of nodes 'from'
            Edge stored = from.hasAFaninEdge(to.num);
            if (stored == null) {
                //First time
                u.myassert(e.other == to.num);
                from.fanin.put(to.num, e);
            } else {
                //Already there. Store the cheapest cost
                double v = stored.cost;
                if (w < v) {
                    stored.cost = w; //Reduced to low cost
                }
            }
        }
    }

    /*
     * Time complexity = THETA(V+E)
     * Space complexity = THETA(V)
     */
    public boolean assertDFS(boolean cycle, ArrayList<Integer> dfsorder) {
        int numv = numV();
        int size = dfsorder.size();
        u.myassert(numv == size);
        if (size != numv) {
            return false;
        }
        GraphType.Type t = type;
        if (t == GraphType.Type.UNDIRECTED || t == GraphType.Type.WEIGHTED_UNDIRECTED) {
            return true;
        }
        if (cycle == false) {
            boolean[] visited = new boolean[numv];
            for (int i = 0; i < numv; ++i) {
                visited[i] = false;
            }

            for (int i = 0; i < size; ++i) {
                int v = dfsorder.get(i);
                Node n = nodes.get(v);
                //fanin = new HashMap<Integer,Edge>();
                //Key is Integer, value is Edge
                for (Edge e : n.fanin.values()) {
                    int k = e.other;
                    if (visited[k] == false) {
                        return false;
                    }
                }
                visited[v] = true;
            }
        }
        return true;
    }


    public void dumpGraph(String name) {
        GraphDump g = new GraphDump(this, name);
    }

    public void buildGraph(String f) {
        GraphBuilder g = new GraphBuilder(this, f);
    }

    public void writeGraphAsDotFile(String f) {
        GraphDot g = new GraphDot(this, f);
    }

    public void dfsUsingTimeStamp(int[] work, boolean[] cycle, ArrayList<Integer> topologicalOrderArray,
        String f) {
        GraphDFSUsingTimeStamp g = new GraphDFSUsingTimeStamp(this, work, cycle, topologicalOrderArray, f);
    }

    public void dijkstra(String name, String startCity, String f, int[] work, double[] cost) {
        GraphDijkstra g = new GraphDijkstra(this, name, startCity, f, work, cost);
    }

    public static void main(String[] args) {
        System.out.println("Use GraphTest.java to test");
    }
}