

/**
 *  题目地址: https://leetcode.cn/problems/find-closest-lcci/solution/
 *
 *  题目描述: 有个内含单词的超大文本文件，给定任意两个不同的单词，找出在这个文件中这两个单词的最短距离(相隔单词数)。如果寻找过程在这个文件中会重复多次，而每次寻找的单词不同，你能对此优化吗?
 *
 */

public class FindClosestLcci {

    // 我的解法, 设置一个int来标记上一次匹配到的其中一个字符,一个String来记录该字符
    // 然后,遍历数组, 每次遇到其中一个就判断是否和上一个匹配字符一致,一致就更新位置,否则计算距离,只需要遍历数组一次.
    public int findClosest(String[] words, String word1, String word2) {
        int minDistance = 10000;
        int matchingIndex = 0;
        int point = 0;
        String pointWord = "";
        while (point < words.length) {
            if (words[point].equals(word1)) {
                if (!words[point].equals(pointWord) && pointWord != "") {
                    minDistance = Math.min(minDistance, point - matchingIndex);
                }
                matchingIndex = point;
                pointWord = words[point];
            } else if (words[point].equals(word2)) {
                if (!words[point].equals(pointWord) && pointWord != "") {
                    minDistance = Math.min(minDistance, point - matchingIndex);
                }
                matchingIndex = point;
                pointWord = words[point];
            }
            point++;
        }
        return minDistance;
    }

    // 简短写法
    public static int findClosest2(String[] ws, String a, String b) {
        int n = ws.length, ans = n;
        for (int i = 0, p = -1, q = -1; i < n; i++) {
            String t = ws[i];
            if (t.equals(a)){ p = i;}
            if (t.equals(b)){ q = i;}
            if (p != -1 && q != -1){ ans = Math.min(ans, Math.abs(p - q));}
        }
        return ans;
    }

    // 总结: 这题暴露了我在写题解的时候的两个问题:
    //      1.对于边界值问题,不敢大方的去使用 -1,等边界值以辅助判断;
    //      2.对于关键的确定距离的比较函数,总想着卡着产生变量的节点去操作(匹配到目标字符时),而不舍得去利用for循环每一次都进行比较.
    //        这就导致了写法的思路从一开始就有差异,最终产出的结果代码变量更多,复杂度更高.
    //
    // 来源: https://leetcode.cn/problems/find-closest-lcci/solution/by-ac_oier-0hv9/
}