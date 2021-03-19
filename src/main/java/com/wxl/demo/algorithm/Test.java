package com.wxl.demo.algorithm;

public class Test {
    public static void main(String[] args) {
        countBits(85723);
    }

    public static int[] countBits(int num) {
        int[]arr = new int[num+1];
        for(int i=0;i<=num;i++){
            int count =0;
            for(int j=31;j>=0;j--){
                System.out.print((i&(1<<j)) == 0?"0":"1");
                if((i&(1<<j)) != 0){
                    count++;
                }
            }
            System.out.println();
            arr[i]=count;
        }
        return arr;
    }
}
