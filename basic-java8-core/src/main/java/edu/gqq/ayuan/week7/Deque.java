package edu.gqq.ayuan.week7;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * File Name: DequeTest.java
 *
 *
 * To Compile: IntUtil.java RandomInt.java Deque.java DequeTest.java
 *
 * @author Jagadeesh Vasudevamurthy
 * @year 2022
 */
class Deque {

    // low bounder
    private int lb;
    // hight bounder
    private int hb;

    private final HashMap<Integer, Integer> idxMap = new HashMap<>();
    private final LinkedList<Integer> linkedList = new LinkedList<>();
    //You can use any data structure you want except Deque like ArrayDeque from Java library
    //All operation must be O(1)

    Deque() {
        lb = 0;
        hb = -1;
    }

    /*
     * MUST BE Time:O(1)
     * MUST BE Space O(1)
     */
    public void addFirst(Integer v) {
        lb--;
        idxMap.put(lb, v);
        linkedList.addFirst(v);
    }

    /*
     * MUST BE Time:O(1)
     * MUST BE Space O(1)
     */
    public void addLast(Integer v) {
        hb++;
        idxMap.put(hb, v);
        linkedList.addLast(v);
    }

    /*
     * MUST BE Time:O(1)
     * MUST BE Space O(1)
     */
    public int removeFirst() {
        if (size() > 0) {
            idxMap.remove(lb);
            lb++;
            Integer val = linkedList.removeFirst();
            return val;
        }
        return Integer.MIN_VALUE;
    }

    /*
     * MUST BE Time:O(1)
     * MUST BE Space O(1)
     */
    public int removeLast() {
        if (size() > 0) {
            idxMap.remove(hb);
            hb--;
            Integer val = linkedList.removeLast();
            return val;
        }
        return Integer.MIN_VALUE;
    }

    /*
     * MUST BE Time:O(1)
     * MUST BE Space O(1)
     */
    public int size() {
        return hb - lb + 1;
    }

    /*
     * MUST BE Time:O(1)
     * MUST BE Space O(1)
     * THIS IS THE HEART OF MY INFO 6205
     * Can you get item at 'pos' in time O(1) and space O(1)
     * For 100% grade, this must work
     */
    public int getV(int pos) {
        return idxMap.get(pos + lb);
    }

    /*************************************************************
     * ALL PRIVATE ROUTINES BELOW
     **************************************************************/

}