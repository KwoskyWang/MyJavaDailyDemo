import java.util.HashSet;
import java.util.Set;


/**
 * 题目地址: https://leetcode.cn/problems/unique-substrings-in-wraparound-string/solution/xi-fa-dai-ni-xue-suan-fa-yi-ci-gao-ding-qian-zhui-/
 *
 * 题目描述: 把字符串 s 看作是 “abcdefghijklmnopqrstuvwxyz” 的无限环绕字符串，所以 s 看起来是这样的：
 *
 *          "...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd...." . 
 *          现在给定另一个字符串 p 。返回 s 中 唯一 的 p 的 非空子串 的数量 。 
 *
 */


public class UniqueSubstringsInWraparoundString {

    public static void main(String[] args) {
        System.out.println(findSubstringInWraproundString("zab"));
    }

    public static int findSubstringInWraproundString(String p) {
        Set<String> set = new HashSet<String>();
        for (int i = 1; i < p.length(); i++) {
            int index = i - 1;
            int j = i;
            if (!set.contains(p.substring(index, i))) {
                set.add(p.substring(index, i));
            }
            while (j < p.length() && (p.charAt(j) - p.charAt(j - 1) == 1 || (p.charAt(j) == 'a' && p.charAt(j) - p.charAt(j - 1) == -25))) {
                if (!set.contains(p.substring(index, j + 1))) {
                    set.add(p.substring(index, j + 1));
                    j++;
                } else {
                    break; //这里如果不加 break,上面的循环可能会走不出来.因为if一直不满足,但是满足while.
                }
            }
        }
        if (!set.contains(p.substring(p.length() - 1, p.length()))) {
            set.add(p.substring(p.length() - 1, p.length()));
        }
        return set.size();
    }
}
