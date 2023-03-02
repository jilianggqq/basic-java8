package edu.gqq.algorithm.leetcode38;

/**
 * File Name: L0038.java
 *
 * @author Jagadeesh Vasudevamurthy
 * @year 2022
 */

/************************************************************
 * CUT AND PASTE ENTIRE FILE IN LEETCODE
 *************************************************************/
public class SolutionL0038 {

    //In LEETCODE SolutionL0038 changed to Solution
    //Nothing can be changed below
    //LEETCODE VERSION
    public String countAndSay(int n) {
        String ans = alg(n);
        return ans;
    }

    //My Version
    public String countAndSay(String n) {
        String ans = alg(n);
        return ans;
    }

    //WRITE YOUR CODE BELOW
    private String alg(String s) {
        if (s.length() == 0) {
            return "";
        }
        int i = 0;
        int cnt = 0;
        char des = s.charAt(i);
        StringBuilder sb = new StringBuilder();
        while (i < s.length()) {
            char curr = s.charAt(i);
            if (curr != des) {
                sb.append(cnt);
                sb.append(des);
                des = curr;
                cnt = 1;
            } else {
                cnt++;
            }
            i++;
        }

        sb.append(cnt);
        sb.append(des);
        return sb.toString();
    }

    private String alg(int n) {
        if (n < 1) {
            System.out.println("n must be larger than 1");
            return "Error";
        }

        String res = "1";

        for (int i = 2; i <= n; i++) {
            res = alg(res);
        }
        return res;

    }

    public static void main(String[] args) {
        System.out.println("Run L0038Test.java");
    }
}



