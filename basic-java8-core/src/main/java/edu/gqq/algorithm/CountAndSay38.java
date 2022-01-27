package edu.gqq.algorithm;

import org.junit.Assert;
import org.junit.Test;

public class CountAndSay38 {

    public String countAndSay(int n) {
        if (n < 1) {
            System.out.println("n must be larger than 1");
            return "Error";
        }

        String res = "1";

        for (int i = 2; i <= n; i++) {
            res = say(res);
        }
        return res;
    }

    public String say(String s) {
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

    @Test
    public void testCountAndSay() {
        Assert.assertEquals("Error", countAndSay(0));
        Assert.assertEquals("11", countAndSay(2));
        Assert.assertEquals("1211", countAndSay(4));
        Assert.assertEquals("111221", countAndSay(5));
        Assert.assertEquals("312211", countAndSay(6));
        Assert.assertEquals("13112221", countAndSay(7));
        Assert.assertEquals("13211311123113112211", countAndSay(10));
    }
}
