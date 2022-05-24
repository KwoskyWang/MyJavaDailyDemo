
// 相关地址：https://leetcode.cn/problems/minimum-moves-to-equal-array-elements-ii/solution/
// 说明：给你一个长度为 n 的整数数组 nums ，返回使所有数组元素相等需要的最少移动数。在一步操作中，你可以使数组中的一个元素加 1 或者减 1 。

// 写法解释:
// 1. 找到中位数
// 2. 累加和中位数的差值

import java.util.Arrays;

public class MinimumMovesToEqualArrayElements {

    public static void main(String[] args) {
        minMoves1(new int[]{1,0,0,8,6});
    }

    // 自己写的,又臭又长
    public static int minMoves1(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        int average = 0;
        int total = 0;
        int min = 0;
        for (int i = 0; i < nums.length; i++) {
            total += nums[i];
        }
        average = total / nums.length;
        total = 0;
        for (int i = 0; i < nums.length; i++) {
            if (Math.abs(average - nums[i]) < Math.abs(average - nums[min])) {
                min = i;
            }
        }
        // ↑↑↑ 花了这么老长一段来找中位值,实际上直接给数组排序, nums.lengt/2 就可以
        for (int i = 0; i < nums.length; i++) {
            total += Math.abs(nums[min] - nums[i]);
        }
        return total;
        // ↑↑↑ 计算值还错了
    }


    // 题解
    public static int minMoves2(int[] nums) {

        Arrays.sort(nums);
        int n = nums.length, ret = 0, x = nums[n / 2];
        for (int i = 0; i < n; i++) {
            ret += Math.abs(nums[i] - x);
        }
        return ret;
    }
}
