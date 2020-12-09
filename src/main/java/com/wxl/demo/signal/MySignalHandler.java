package com.wxl.demo.signal;

import sun.misc.Signal;
import sun.misc.SignalHandler;

/**
 * 信号处理实例
 * 由于对信号的处理是多线程的，所以应保证信号处理实例SignalHandler应该是线程安全的
 *  SpringBoot可以采用信号监听
 * public class xxApplication{
 *
 *    public static void main(String[] args) {
 *         xxApplication app = new xxApplication();
 *         app.doStart(args);
 *         KillHandler killHandler = new KillHandler(app);
 *         killHandler.registerSignal("TERM");
 *    }
 *
 *    private doStart(args)
 *    private synchronized doStop()
 *
 *    static class KillHandler implements SignalHandler {
 *         xxApplication app;
 *         public KillHandler(xxApplication app) {
 *             this.app = app;
 *         }
 *           @Override
 *         public void handle(Signal signal) {
 *
 *             if (signal.getName().equals("TERM")) {
 *                 app.doStop();
 *             }
 *         }
 *    }
 * }

 *
 *
 */
public class MySignalHandler implements SignalHandler {

    public static void main(String[] args) throws Exception {
        //信号处理实例
        MySignalHandler signalHandler = new MySignalHandler();

        Signal.handle(new Signal("TERM"),signalHandler);// kill or kill -15  pid
        Signal.handle(new Signal("INT"),signalHandler);// kill -2

        //由于对信号的处理是多线程的，所以应保证信号处理实例SignalHandler应该是线程安全的
        System.out.println("[Thread:"+Thread.currentThread().getName() + "] is sleep" );

        while(true) {
            Thread.sleep(1000);
        }

    }

    @Override
    public void handle(Signal signal) {
        // 信号量名称
        String name = signal.getName();
        // 信号量数值
        int number = signal.getNumber();

        // 当前线程名
        String currentThreadName = Thread.currentThread().getName();
        //TODO 由于对信号的处理是多线程的，所以应保证信号处理实例SignalHandler应该是线程安全的
        // 处理应该是线程安全的 synchronized
        System.out.println("[Thread:"+currentThreadName + "] receved signal: " + name + " == kill -" + number);
        if(name.equals("TERM")){
            System.exit(0);
        }
    }
}

