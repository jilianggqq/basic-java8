package edu.gqq.ayuan.week6;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * File Name: L0322.java
 *
 * @author
 * @year 2021
 */

/************************************************************
 * CUT AND PASTE ENTIRE FILE IN LEETCODE
 *************************************************************/
class Solution {

    //Nothing can be changed below
    //LEETCODE VERSION
    public int coinChange(int[] coins, int amount) {
        int[] work = {0};
        ArrayList<Integer> ans = new ArrayList<Integer>();
        boolean show = false;
        L0322 h = new L0322(coins, amount, ans, work, show);
        int numChange = ans.size();
        if (numChange == 1) {
            if (ans.get(0) == -1) {
                numChange = -1;
            }
        }
        return numChange;
    }
}

/************************************************************
 WRITE CLASS L0322
 *************************************************************/
class L0322 {

    private int[] d;
    private int amount;
    private ArrayList<Integer> ans;
    private int[] work;
    private boolean show;
    //You can have any number of private variables below
    ArrayList<Integer>[] results;

    L0322(int[] coins, int amount, ArrayList<Integer> ans, int[] work, boolean show) {
        //NOTHING CAN BE CHANGED HERE
        this.d = coins;
        this.amount = amount;
        this.ans = ans;
        this.work = work;
        this.show = show;
        results = new ArrayList[amount + 1];
        //MUST WRITE 2 routines
        alg(); //1
        getSolution(); //2
        if (show) {
            System.out.println("WORK DONE = " + work[0]);
        }
    }

    private void incrementWork() {
        work[0]++;
    }

    /*
     * WRITE YOUR CODE HERE. YOU CAN HAVE ANY NUMBER
     * OF PRIVATE FUNCTIONS AND PRIVATE MEMBER FUNCTION
     */


    private void getSolution() {
        if (!canbeMadeup(results[amount])) {
            ans.clear();
            ans.add(-1);
        } else {
            for (Integer res : results[amount]) {
                ans.add(res);
            }
        }
        StringJoiner joiner = new StringJoiner(",");
        printLog();
    }

    private void printLog() {
        System.out.println(String.format("Give minimum change for %s cents using coins %s", amount,
            "{" + Arrays.stream(this.d).mapToObj(x -> x + "").collect(Collectors.joining(",")) + "}"));

        String iArray =
            IntStream.rangeClosed(0, amount).mapToObj(x -> convertToPrintStr(x)).collect(Collectors.joining(
            ""));
        System.out.println(String.format("i =     %s", iArray));

        String mArray = Arrays.stream(results).
            map(res -> convertToPrintStr(getStepNumber(res)))
            .collect(Collectors.joining(""));
        System.out.println(String.format("m array %s", mArray));

        String kArray = Arrays.stream(results).
            map(res -> convertToPrintStr(getLastPickedCoin(res)))
            .collect(Collectors.joining(""));
        System.out.println(String.format("k array %s", kArray));

    }

    private String convertToPrintStr(int num) {
        String res = num + "";
        int len = res.length();
        for (int i = 0; i < 4 - len; i++) {
            res = " " + res;
        }
        return res;
    }

    private void alg() {
//        dp arrays. For every amount, it contains coin result.
        results[0] = new ArrayList<>();
        for (int i = 1; i < results.length; i++) {
            incrementWork();
            results[i] = new ArrayList<>();
            results[i].add(Integer.MAX_VALUE);
        }

        for (int i = 1; i < results.length; i++) {
            incrementWork();
            for (int coin : d) {
                incrementWork();
                if (i == coin) {
                    setOne(results[i], coin);
                    break;
                } else if (i > coin && canbeMadeup(results[i - coin])) {
                    if (getNumber(results[i]) > getNumber(results[i - coin]) + 1) {
                        // set results[i] as results[i-coin] + coin
                        updateResult(results, i, i - coin, coin);
                    }
                }
            }
        }
    }

    private void updateResult(ArrayList<Integer>[] results, int currIdx, int preIdx, int coin) {
        results[currIdx].clear();
        results[currIdx].addAll(results[preIdx]);
        results[currIdx].add(coin);
    }

    private boolean canbeMadeup(ArrayList<Integer> result) {
        return getNumber(result) != Integer.MAX_VALUE;
    }

    private int getLastPickedCoin(ArrayList<Integer> res) {
        if (res.size() == 0 || !canbeMadeup(res)) {
            return 0;
        }
        return res.get(res.size() - 1);
    }

    private int getNumber(ArrayList<Integer> result) {
        if (result.size() == 1 && result.get(0) == Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        return result.size();
    }

    private int getStepNumber(ArrayList<Integer> result) {
        if (result.size() == 1 && result.get(0) == Integer.MAX_VALUE) {
            return -1;
        }
        return result.size();
    }

    private void setOne(ArrayList<Integer> res, int coin) {
        res.clear();
        res.add(coin);
    }


    public static void main(String[] args) {
        System.out.println("Run L0322Test.java");
//        L0322.alg();
//        System.out.println("------------Weird Coins--------------------");
        ArrayList<Integer> anws = new ArrayList();
        int[] step = new int[1];
//        int[] w = {1, 5, 10, 12};
//        int amount = 16
        int[] w = {1};
        int amount = 0;
//        computeChange(w, 16, 3);
        Solution solution = new Solution();
        int num = solution.coinChange(w, amount);
        System.out.println(num);
    }
}
