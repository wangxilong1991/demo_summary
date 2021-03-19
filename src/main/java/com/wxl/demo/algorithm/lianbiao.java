package com.wxl.demo.algorithm;

public class lianbiao {


}

/**
 * 两个有序链表合并
 */
class Solution3 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(-1);
        ListNode temp = head;
        while(l1!=null && l2!=null){
            if(l1.val<=l2.val){
                temp.next = l1;
                l1 = l1.next;
            }else{
                temp.next = l2;
                l2 = l2.next;
            }
            temp = temp.next;
        }
        temp.next = l1==null?l2:l1;
        return head.next;
    }
}

/**
 * 删除链表倒数第n个节点
 */
class Solution2 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode first = head;
        ListNode second = head;
        int step = 0;

        while(first.next!=null){
            first = first.next;
            step++;
            if(step > n){
                second = second.next;
            }
        }
        if(step + 1  == n){
            return head.next;
        }else{
            if(n == 1){
                second.next = null;
            }else{
                second.next = second.next.next;
            }
            return head;
        }
    }
}

/**
 * 两数相假
 */
class Solution1 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null, tail = null;
        int n1 =0,n2=0,sum = 0,carry = 0;

        while(l1!=null || l2!=null){
            n1 = l1==null?0:l1.val;
            n2 = l2==null?0:l2.val;
            sum = n1+n2+carry;

            if(head == null){
                head = tail = new ListNode(sum%10);
            }else{
                tail.next = new ListNode(sum%10);
                tail = tail.next;
            }

            if(l1!=null){
                l1 = l1.next;
            }
            if(l2!=null){
                l2 = l2.next;
            }
            carry = sum/10;
        }
        if(carry>0){
            tail.next = new ListNode(carry);
        }

        return head;
    }
}