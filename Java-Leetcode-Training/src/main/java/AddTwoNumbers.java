


/**
 * 题目地址: https://leetcode.cn/problems/add-two-numbers/
 *
 */
public class AddTwoNumbers {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        System.out.println(addTwoNumbers1(l1,l2));
    }

    public static ListNode addTwoNumbers1(ListNode l1, ListNode l2){
        ListNode head = null, tail = null;
        int carry = 0;
        while (l1!=null || l2!=null){
            int n1 = l1!=null ? l1.val : 0;
            int n2 = l2!=null ? l2.val : 0;
            int sum = n1 + n2 + carry;
            if (head == null){
                head = tail = new ListNode(sum%10);
            }else {
                tail.next = new ListNode(sum%10);
                tail = tail.next;
            }
            carry = sum/10;
            if (l1!=null){
                l1 = l1.next;
            }
            if (l2!=null){
                l2 = l2.next;
            }
        }
        if (carry>0){
            tail.next = new ListNode(carry);
        }
        return head;
    }

    // 算出两个链表代表的数, 然后相加, 再还原链表, 这样做步骤太多了, 可以直接在原链表上操作, 减少步骤
    public static int addTwoNumbers2(ListNode l1, ListNode l2) {
        int l1_multiple = 1, l2_multiple = 1;
        int l1_sum = 0, l2_sum = 0;
        ListNode l1Node = l1, l2Node = l2;
        while(l1Node!=null){
            l1_sum = l1_sum + l1_multiple * l1Node.val;
            l1_multiple *= 10;
            l1Node = l1Node.next;
        }
        while(l2Node!=null){
            l2_sum = l2_sum + l2_multiple * l2Node.val;
            l2_multiple *= 10;
            l2Node = l2Node.next;
        }
        int sum = l1_sum + l2_sum;
        return sum;
    }

}

class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}