package com.fansin.base;

/**
 * Created by zhaofeng on 17-4-12.
 */
public class ClassExtend {


}
class Parent{

    /**

     父类静态方法 静态资源与代码顺序相关,方法块和成员变量不分先后
     父类静态成员变量......
     子类静态方法
     子类静态成员变量......
     父类成员变量......
     父类构造方法
     子类成员变量......
     子类构造方法

     */
    public static void main(String[] args) {
        System.out.println(Parent.i);
        Son son = new Son();
//        son.say();
//        son.saySon();
//        son.hi();
    }

    static {
        i = 6;
        System.out.println("父类静态方法 静态资源与代码顺序相关");
    }

    static int i=100;
    static {
        i = i/2;
    }

    public Parent(){
        System.out.println("父类构造方法");
    }

    private Object value = new Child("父类");

    private static Object valueStatic = new Child("父类静态");

    public void say(){
        System.out.println("父类普通方法 say");
    }
    public void hi(){
        System.out.println("父类普通方法 hi");
    }

}

class Child{
    public Child(String str){
        System.out.println(str+"成员变量......");
    }
}

class Son extends Parent{
    static {
        System.out.println("子类静态方法");
    }

    private Object valueSon = new Child("子类");

    private static Object valueStaticSon = new Child("子类静态");

    public Son(){
        System.out.println("子类构造方法");
    }


    public void saySon(){
        System.out.println("子类普通方法");
    }

    @Override
    public void hi() {
        System.out.println("子类重写方法");
    }
}