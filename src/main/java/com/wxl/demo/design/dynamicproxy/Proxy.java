package com.wxl.demo.design.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 动态代理只能代理接口的原因
 * 生成的代理类居然继承了Proxy，我们知道java是单继承的，所以JDK动态代理只能代理接口
 */
public class Proxy {

    public static void main(String[] args) {

        Hello h = new HelloWorld();
        h.morning("Tom");
        System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles","true");

        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println(method);

                if(method.getName().equals("morning")){
                    System.out.println("Good morning, " + args[0]);
                }
                return null;
            }
        };

        Hello proxy = (Hello) java.lang.reflect.Proxy.newProxyInstance(Hello.class.getClassLoader(),new Class[]{Hello.class},handler);
        proxy.morning("jerry");



    }
}

class HelloProxy implements InvocationHandler{

    private Hello hello;

    public HelloProxy(Hello hello) {
        this.hello = hello;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {


        return null;
    }
}



class HelloWorld implements Hello {
    @Override
    public void morning(String name) {
        System.out.println("Good morning, " + name);
    }
}

interface  Hello{
    void morning(String name);
}