package edu.gqq.ayuan.loop;

public class TianganDizhi {

    public static void main(String[] args) {
        String[] tianGans = {"甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸"};
        String[] diZhis = {"子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥"};
        for (int i = 0, j = 0; true; i++, j++) {
            int idxI = i % tianGans.length;
            int idxJ = j % diZhis.length;
            if (idxI == 0 && idxJ == 0 && i != 0 && j != 0) {
                break;
            }
            System.out.print(tianGans[idxI] + diZhis[idxJ] + " ");
            if (idxI == 9) {
                System.out.println();
            }
        }
    }
}
