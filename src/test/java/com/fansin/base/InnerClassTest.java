package com.fansin.base;

import static org.junit.Assert.*;

/**
 * Created by zhaofeng on 17-4-12.
 */
public class InnerClassTest {

    public void testInner(){
        InnerClass inner = new InnerClass();
        //外部访问不了非静态内部类
        InnerClass.InnerClass1 inner1 = inner.new InnerClass1();
    }

}