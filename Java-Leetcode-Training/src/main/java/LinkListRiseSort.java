
// 题目地址：https://leetcode-cn.com/problems/7WHec2/
// 题目要求：给定链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表。

public class LinkListRiseSort {
    // Definition for singly-linked list.
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode sortList(ListNode head) {

//    暴力写法,遍历N次,通过依次对比交换获得最大值
//        int swapNode;
//        int linkLength = 1;
//        ListNode root = head;
//        if(head.next==null){ return head; }
//        while (head.next!=null){ //先遍历一遍，找到list的长度
//
//            if(head.val>head.next.val){
//                swapNode = head.next.val;
//                head.next.val = head.val;
//                head.val = swapNode;
//            }
//            head = head.next;
//            linkLength++;
//        }
//        head = root;    //把head重新指向头节点
//        for (int round=linkLength-1;round>1;round--){ //然后按照长度每次-1来遍历N次，每次找到第N大的数
//            int count = round;
//            int start = 1;
//            while (start<count){
//                if(head.val>head.next.val){
//                    swapNode = head.next.val;
//                    head.next.val = head.val;
//                    head.val = swapNode;
//                }
//                head = head.next;
//                start++;
//            }
//            head = root;    // 每次遍历完把head重新指向头节点
//        }
//

        return head;
    }
}

// 笔记：
// 第一想法是通过遍历N次来每次找到最大的值往后排, 时间复杂度 2/n^2, 实测超过时间限制,太复杂了

