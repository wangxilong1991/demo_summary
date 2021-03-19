package com.wxl.demo.optional;

import java.util.Optional;

public class OptionTest {
    public static void main(String[] args) {

        String s = Optional.ofNullable("456").orElse("123");
        String tmp = null;
        String s1 = Optional.ofNullable(tmp).orElse("123");

        System.out.println(s);
        System.out.println(s1);
    }
}
