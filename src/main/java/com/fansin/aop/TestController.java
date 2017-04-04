package com.fansin.aop;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhaofeng on 17-4-1.
 */
@RestController
public class TestController {

    @RequestMapping("/")
    public void index(){
        System.out.println("Hello World!");
    }

    @RequestMapping("/a")
    public  void toA(){
        System.out.println("A");
    }


    @RequestMapping("/b")
    public  void toB(){
        System.out.println("B");
    }


}
