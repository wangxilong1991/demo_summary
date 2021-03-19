package com.wxl.demo.algorithm;

/**
 * 比特位计数
 */
public class BitCounts {

    /**
     * x&(x-1) 相当于去二进制位最后一个1
     * @param num
     * @return
     */
    public int[] countBits(int num) {
        int[]arr = new int[num+1];
        for(int i=0;i<=num;i++){
            int count =0;
            int n =i;
            while(n>0){
                n = n&(n-1);
                count++;
            }
            //效率低
//            for(int j=31;j>=0;j--){
////                System.out.print((i&(1<<j)) == 0?"0":"1");
//                if((i&(1<<j)) != 0){
//                    count++;
//                }
//            }
//            System.out.println();
            arr[i]=count;
        }
        return arr;
    }

    /**
     * 动态规划   缓存计算过的存起来
     * @param num
     * @return
     */
    public int[] countBits2(int num) {
        int[]arr = new int[num+1];
        for(int i=1;i<=num;i++){
            arr[i] = arr[ i>>1] + (i&1);
        }
        return arr;
    }
}
