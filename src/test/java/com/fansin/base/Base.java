package com.fansin.base;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by zhaofeng on 17-4-11.
 */
public class Base {
    public class Value{
        private int i = 100;

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }
    }

    @Test
    public void testArray(){
        char value[];
//        System.out.println(value.length);
        value = new char[2];
        System.out.println(value.length);
        value = new char[6];
        System.out.println(value.length);
    }

    @Test
    public void testValue() {
        Value v = new Value();
        v.i++;
        Value v1 = v;
        v1.i++;
        Assert.assertEquals(102,v.i);
        Assert.assertEquals(102,v1.i);

        Value vv = null;
        change(vv);
        System.out.println("null引用无法传递!"+vv.getI());
    }

    public void change(Value value){
        if (value == null){
            value = new Value();
            value.setI(222);
        }
    }

    @Test
    public void testRef(){
        int[] array = new int[]{123};//限定了数组大小为1
//        array[0] = 20;
        change(array);//数组也和对象一样属于引用传递
        Assert.assertEquals(1,array[0]);
    }

    public void change(int[] array){//array 形参 引用传递
        array[0] = 0001;

    }
}
