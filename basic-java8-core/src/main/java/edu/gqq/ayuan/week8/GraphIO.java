package edu.gqq.ayuan.week8;

import edu.gqq.ayuan.IntUtil;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * File Name: GraphIO.java
 *
 * @author Jagadeesh Vasudevamurthy
 * @year 2021
 */

/*********************************************************************
 NOTHING CAN BE CHANGED IN THIS FILE
 **********************************************************************/
class GraphIO {

    private HashMap<String, Integer> hm = new HashMap<String, Integer>(); //String 2 Integer
    private ArrayList<String> nodeNames = new ArrayList<>(); //Given an integer get name
    private IntUtil u = new IntUtil();

    GraphIO() {

    }

    /*
     * TIME: THETA(1)
     * SPACE: THETA(1)
     */
    public int insertOrFind(String name, boolean mustbethere) {
        boolean f = hm.containsKey(name);
        if (f) {
            return (hm.get(name));
        }
        if (mustbethere) {
            u.myassert(false);
        }
        //Not in the hash. Insert in hash
        int n = hm.size();
        int nl = nodeNames.size();
        u.myassert(n == nl);
        hm.put(name, n); //THETA(1)
        //Given an unique number you can get name in THETA(1)
        nodeNames.add(name);
        return n;
    }

    /*
     * TIME: THETA(1)
     * SPACE: THETA(1)
     */
    public String getRealName(int i) {
        int nl = nodeNames.size();
        u.myassert(i >= 0 && i < nl);
        return nodeNames.get(i);
    }

    /*
     * TIME: THETA(1)
     * SPACE: THETA(1)
     */
    public int graphHasANode(String name) {
        boolean f = hm.containsKey(name);
        if (!f) {
            return -1;
        }
        int nl = nodeNames.size();
        int i = hm.get(name);
        u.myassert(i >= 0 && i < nl);
        return i;
    }
}
