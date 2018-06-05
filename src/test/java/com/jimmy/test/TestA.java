package com.jimmy.test;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

public class TestA {

	TestB b;
	private TestC c;
	
	@Test
	public void test1(){
		c = new TestC();
		b = new TestB(c);
		System.out.println(b.getC());
	}
	
	public void test2() throws MalformedURLException{
		//下面写法有问题
//		Soup soup = Soup.getInstance();
		
		URL url = new URL("https://www.baidu.com"); 
	}

	public TestB getB() {
		return b;
	}

	public void setB(TestB b) {
		this.b = b;
	}
	
}
