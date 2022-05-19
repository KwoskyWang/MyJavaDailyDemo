
// 相关地址：https://leetcode.cn/problems/kth-smallest-number-in-multiplication-table/
// 说明：高度m 、宽度n 的一张 m * n的乘法表，以及正整数k，你需要返回表中第k 小的数字。

// 写法解释:
// 1. 第K小,可以转换思路为,小于等于该数的有k个.
// 2. 因为是乘法表,每一行有 i/k个数小于k(每行最多为n个)
// 3. 乘法表里面一共有 mxn个数,看做一个数组,分前中后三个指针,对应的小于中间点的个数有k1个
// 4. 如果 k<k1, 那么移动三个指针到  左侧区间, 否则移动到右侧区间(二分法)(左侧记得+1,不然只剩三个点时就移不动了)
// 5. mxn中的数可能会不存在于乘法表中,需要找到满足条件的最小的值,这个最小的值肯定在乘法表里

public class NumberKInMultTable {
    public static void main(String[] args) {
        findKthNumber(5,5,10);
    }

    public static int findKthNumber(int m, int n, int k) {
        int left = 1;
        int right = m*n;
        while(left<right){
            int mid = (left+right)/2;
            if(k<=getSmallerCount(m,n,mid)){
                right = mid;
            }else{
                left = mid+1;
            }
        }
        return left;
    }

    public static int getSmallerCount(int m, int n, int k){
        int i = 1;
        int count = 0;
        // while(k/i>=1){  这样写有问题,因为可能k/i 这里的i可能会超过m的限制
        while(i<=m){
            if((k/i)>n){
                count += n;
            }else{
                count += k/i;
            }
            i++;
        }
        return count;
    }
}
