import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 题目地址:  https://leetcode.cn/problems/longest-substring-without-repeating-characters/
 * <p>
 * 题目说明: 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 */
public class LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
    }

    // 用时 72ms
    public static int lengthOfLongestSubstring(String s) {
        int stringLength = s.length(), maxSonLength = 0, head = 0, tail = 0;
        Map<Character, Integer> charMap = new HashMap<>(); // 通过HashMap辅助判重
        while (tail != stringLength) { // 如果滑动窗口右侧没有触达字符串长度
            if (!charMap.containsKey(s.charAt(tail))) { //map没有包含该字符则加入
                charMap.put(s.charAt(tail), tail);
                tail++;
                maxSonLength = Math.max((tail - head), maxSonLength); // 更新最长子串长度
            } else {    // 如果发现重复字符,就重置head,tail指针,清空hashMap并重新添加
                head = charMap.get(s.charAt(tail)) + 1;
                tail = head;
                charMap.clear();
            }
        }
        return maxSonLength;
    }

    // 用时 6ms, 实际上滑动窗口, 每次更新 head指针的时候, HashMap不需要清空, 只需要更新为
    public static int lengthOfLongestSubstring2(String s) {
        HashMap<Character,Integer> hash = new HashMap<>();
        int preIndex = 0;
        int max = 0;
        for(int i=0;i<s.length();i++){ //遍历字符串
            if(hash.containsKey(s.charAt(i))){ //通过map存放已经拿到的字符串,移动数组窗口,不用清空已有字符串
                preIndex = Math.max(preIndex,hash.get(s.charAt(i))+1); //发现重复字符,移动preIndex(上一个重复字符出现的位置)
            }
            max = Math.max(max,i-preIndex+1);//确定最大窗口大小
            hash.put(s.charAt(i),i);
        }
        return max;
    }
}

// 题解-self: keyWord '滑动窗口'; 设置双指针确定窗口的大小, 通过HashMap判重, 遇到重复字符则移动窗口位置.
//