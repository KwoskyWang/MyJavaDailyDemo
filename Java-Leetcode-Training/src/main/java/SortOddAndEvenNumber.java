
// 题目地址：https://leetcode-cn.com/problems/sort-array-by-parity/
// 题目要求：给你一个整数数组 nums，将 nums 中的的所有偶数元素移动到数组的前面，后跟所有奇数元素。

public class SortOddAndEvenNumber {

    public int[] sortArrayByParity(int[] nums) {
        int length = nums.length-1;
        int left = 0;
        while(left<length){
            while(left<length && nums[left]%2==0){
                left++;
            }
            while(left<length && nums[length]%2==1){
                length--;
            }
            if(left<length){
                int temp = nums[left];
                nums[left] = nums[length];
                nums[length] = temp;
                left++;
                length--;
            }
        }
        return nums;
    }

}

// 笔记
// 我第一念头想的就是通过一遍遍历, 然后通过交换来实现, 追求最小的时间/空间负责度, 忽略了其实还有另外两种解法, 差异不大, 但是写起来要简便很多
//    1. 原地交换:
//         双指针, 双指针的时候不要害怕使用 while, 判断条件也都给它加上, 不要有心理压力, 有限制总比没有限制好, 另外注意题目里面的判断条件, nums[left]%2==0时是偶数, left++; 不要搞反了
//    2. 两次遍历:
//         新建一个数组, 第一次遍历把偶数拿出来, 第二次遍历把奇数都拿出来, 时间上没那么快, 但是简单
//    3. 双指针 + 一次遍历:
//         左右侧各一个指针, 但是这个指针是指向我们新建的一个数组, 遍历原数组, 偶数往 left插, 奇数往 right插, 同时移动指针, 又快又简单