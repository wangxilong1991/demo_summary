package com.wxl.demo.algorithm;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class PrintABC {

//    public static void main(String[] args) {
//        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(3,3,2, TimeUnit.SECONDS,new LinkedBlockingQueue<>());
//        poolExecutor.submit(new ThreadDemo("a"));
//        poolExecutor.submit(new ThreadDemo("b"));
//        poolExecutor.submit(new ThreadDemo("c"));
//        poolExecutor.shutdown();
//    }
//
//    public static class ThreadDemo implements Runnable{
//        private static AtomicInteger count = new AtomicInteger(0);
//        private static final String[] str = {"a","b","c"};
//        private static final Integer max = 30;
//        private String name;
//        public ThreadDemo(String name){
//            this.name = name;
//        }
//        @Override
//        public void run() {
//            while (count.get() < max){
//                if (this.name.equals(str[(count.get()%3)])){
//                    print(this.name);
//                }
//            }
//        }
//        private void print(String name){
//            System.out.println(name);
//            count.getAndIncrement();
//        }
//    }

    public static void main(String[] args) throws InterruptedException {




    }

    public static class ThreadDemo implements Runnable{


        @Override
        public void run() {

        }
    }





}
