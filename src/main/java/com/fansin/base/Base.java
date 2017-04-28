package com.fansin.base;

/**
 * Created by zhaofeng on 17-4-11.
 */
public class Base {

    public static void main(String[] args) {
        double value  = Math.cos(Math.toRadians(30));
        System.out.println("余弦值30 "+ value );
        ((Object)null).toString();//运行时报空指针异常

    }



}
