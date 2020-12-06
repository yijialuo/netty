package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KMP {
    public static void main(String[] args) {
//        String s = "ABABCABAA";
////        int[] prefixTable=prefixTable(s.toCharArray());
////        System.out.println(Arrays.toString(prefixTable));
////        movPrefixTable(prefixTable);
////        System.out.println(Arrays.toString(prefixTable));


//        int[] flowerbed = {0, 1, 0};
//        int n = 1;
//        int[] flowerbed = {1,0,0,0,1};
//        int n = 1;
        int[] flowerbed ={1,0,0,0,1};
        int n=2;
        System.out.println(canPlaceFlowers(flowerbed, n));
    }

    static public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int i = 0;//种花的位置，从前往后种
        //n不剩0,就继续种
        while (n != 0) {
            if(i>flowerbed.length-1)
                return false;
            //第一个位置种花
            if(i==0){
                //只有一个位置，且没有种花  或者  第一个位置没花，第二个位置没花
                if(flowerbed[0]==0&&flowerbed.length==1||flowerbed[0]==0&&flowerbed[1]==0){
                    //种花
                    n--;
                    //种花加2
                    i=i+2;
                }else {
                    i=i+1;
                }
            }else if(i==flowerbed.length-1){
                //最后一个位置种花,判断最后一个前一个种花没有,同时最后一个位置没花
                if(i-1>=0&&flowerbed[i-1]==0&&flowerbed[i]==0){
                    //种花
                    n--;
                    i=i+2;
                }else {
                    i=i+1;
                }
            }else {
                //不是前面也不是最后种花
                //前面后面都不能种花
                if(flowerbed[i]==0&&flowerbed[i+1]==0&&flowerbed[i-1]==0){
                    //种花
                    n--;
                    i=i+2;
                }else {
                    i++;
                }
            }
        }
        //能够跳出循环，表示能种完
        return true;
    }


    //判断字符串各个字符是不是唯一值
    static public boolean isOnlyChar(String s) {
        for (Character c : s.toCharArray()) {
            // 最后和最前不一样，则不是唯一串
            if (s.indexOf(c) != s.lastIndexOf(c)) {
                return false;
            }
        }
        return true;
    }

    // kmp匹配
    static void kmp(String rou, String tar) {
        int[] fixtable = prefixTable(tar.toCharArray());
        movPrefixTable(fixtable);
        // i原串索引，j目标串索引
        int i = 0, j = 0;
        char[] rouChars = rou.toCharArray();
        char[] tarChars = tar.toCharArray();
        int tarLen = tarChars.length;
        while (i < rou.length()) {
            //匹配成功的标志，j移动到最后一位，且最后一位相同
            if (j == tarLen - 1 && rouChars[i] == tarChars[j]) {
                System.out.println("匹配到一处：" + (i - j));
                //继续往后面找
                j = fixtable[j];
                // j移动到-1，j和i同时++
                if (j == -1) {
                    i++;
                    j++;
                }
            }
            // 匹配到,同时往后移动
            if (rouChars[i] == tarChars[j]) {
                i++;
                j++;
            } else {
                // 匹配不到，j通过fixtable表移动到前面去
                j = fixtable[j];
                // j移动到-1，j和i同时++
                if (j == -1) {
                    i++;
                    j++;
                }
            }

        }
    }

    static void movPrefixTable(int[] prefixTable) {
        for (int i = prefixTable.length - 1; i > 0; i--) { //最后一次运行i=1，i=0不运行
            prefixTable[i] = prefixTable[i - 1];
        }
        prefixTable[0] = -1;
    }

    static int[] prefixTable2(char[] pattern) {
        int[] prefixTable = new int[pattern.length];
        prefixTable[0] = 0;
        int len = 0;
        int i = 1;
        while (i < pattern.length) {
            if (pattern[i] == pattern[len]) {
                len++;
                prefixTable[i] = len;
            } else {
                // 不相等
                if (len > 0) {
                    //大于0，找后面的
                    len = prefixTable[len - 1];
                } else {
                    prefixTable[i] = len;
                }
            }
            i++;
        }
        return prefixTable;
    }

    static int[] prefixTable(char[] pattern) {
        int[] prefix = new int[pattern.length];
        prefix[0] = 0;
        //前面那个 prifix的值，最开始是0
        int len = 0;
        int i = 1;
        while (i < pattern.length) {
            if (pattern[i] == pattern[len]) {
                len++;
                prefix[i] = len;
            } else {//如果不等
                if (len > 0) {
                    // 如果大于0，取前面的
                    len = prefix[len - 1];
                } else {
                    // 否则就是0
                    prefix[i] = len;
                }
            }
            i++;
        }
        return prefix;
    }
}
