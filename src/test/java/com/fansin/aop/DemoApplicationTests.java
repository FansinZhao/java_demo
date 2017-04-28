package com.fansin.aop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Test
	public void contextLoads() {


		System.out.println(trycatch());

	}

	public static void main(String[] args) {
		System.out.println(trycatch());
	}


	public static String trycatch(){

		try{
			return "b";
		}catch (Exception e){
			return "c";
		}finally {
			System.out.println("A");
		}

	}

}
