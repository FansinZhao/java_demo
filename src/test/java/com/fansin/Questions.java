package com.fansin;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by zhaofeng on 17-4-30.
 */
public class Questions {
    @Test
    public  void testVariable(){
        int i = 0;
        i = i++;
        Assert.assertEquals(0,i);
        add(i++);
        Assert.assertEquals(1,i);
    }

    private void add(int i){
        i++;
    }

    @Test
    public void testClassVariable(){
        Base base = new Sub();
    }

    @Test
    public void testStr(){
        System.out.println("".substring(-1));
    }

}


class Base {
    private  String base = "Base";

    public Base() {
        callF();
    }

    public void callF(){
        System.out.println(base);
    }
}
class Sub extends Base{
    private  String base ="sub";
    public Sub() {
        super();
    }
    public void callF(){
        Assert.assertEquals(null,base);
        System.out.println(base);
    }
}