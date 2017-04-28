package com.fansin.base;

/**
 * Created by zhaofeng on 17-4-14.
 */
public class StringDemo {

    public static void main(String[] args) {
        String str = "123456";
//        jdk 新特性
        int i = 1_0_0_2;
        int j = 0b110;
        System.out.println(str + i + " "+j);

        System.out.println("hi".hashCode()+">"+"ok".hashCode());
        String str1 = "hi"+"ok";
        String str2 = "hi";
        String str3 = "ok";
        System.out.printf("%s %s %s \n",str1.hashCode(),str2.hashCode(),str3.hashCode());


    }


}
