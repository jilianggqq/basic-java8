package edu.gqq.ayuan.week8; /**
 * File Name: GraphType.java
 *
 * @author Jagadeesh Vasudevamurthy
 * @year 2021
 */

/*********************************************************************
 NOTHING CAN BE CHANGED IN THIS FILE
 **********************************************************************/
class GraphType {

    public enum Type {
        NONE, UNDIRECTED, DIRECTED, WEIGHTED_UNDIRECTED, WEIGHTED_DIRECTED
    }

    static String gtype(GraphType.Type t) {
        if (t == GraphType.Type.UNDIRECTED) {
            return "UNDIRECTED";
        }
        if (t == GraphType.Type.DIRECTED) {
            return "DIRECTED";
        }
        if (t == GraphType.Type.WEIGHTED_UNDIRECTED) {
            return "WEIGHTED_UNDIRECTED";
        }
        if (t == GraphType.Type.WEIGHTED_DIRECTED) {
            return "WEIGHTED_DIRECTED";
        }
        return "NONE";
    }
}