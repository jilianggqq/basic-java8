package edu.gqq.ayuan.loop;

public class TianganDizhi2 {

    public static void main(String[] args) {
        String[] tianGans = {"甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸"};
        String[] diZhis = {"子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥"};
        int i = 0, j = 0;
        int cnt = 1;

        while (cnt <= 60) {
            int idxI = i % tianGans.length;
            int idxJ = j % diZhis.length;
            System.out.print(tianGans[idxI] + diZhis[idxJ] + " ");
            if (cnt % 10 == 0) System.out.println();
            i++;
            j++;
            cnt++;
        }
    }
}
