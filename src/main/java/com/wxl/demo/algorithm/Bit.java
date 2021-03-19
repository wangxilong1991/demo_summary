package com.wxl.demo.algorithm;

import java.util.HashMap;

public class Bit {

    public static void print(int n){

        for (int i = 31; i >=0; i--) {
            System.out.print((n&(1<<i)) == 0?"0":"1");
        }
        System.out.println();
    }

    public static void main(String[] args) {
//        print(Integer.MAX_VALUE);
//        print(Integer.MIN_VALUE);
//        print(8);
//        print(-1);
        //负数二进制位是取反+1  底层走一套逻辑
//        int a = 1234566;
//        int b = 454534534;
//        print(a);
//        print(b);
//        System.out.println("==========");;
//        print(a|b);
//        print(a&b);
//        print(~a);
        ListNode l1 = new ListNode(0,new ListNode(1));
        System.out.println(l1);
        ListNode temp = l1;
        System.out.println(temp);
        l1 = l1.next;
        System.out.println(l1);
    }
}
 class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }