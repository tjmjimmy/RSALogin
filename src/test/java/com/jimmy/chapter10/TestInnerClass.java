package com.jimmy.chapter10;

import org.junit.Test;

import com.jimmy.chapter10.Outer.Inner;
import com.jimmy.chapter10.Outer.InnerContent;

public class TestInnerClass {

	@Test
	public void test1(){
		Outer o = new Outer();
		Inner inner = o.getInner();
		System.err.println(inner);
	}
	
	@Test
	public void test2(){
		InnerContent ic = new InnerContent();
		System.out.println(ic.value());
		Outer out = new Outer(); 
		Inner in = out.new Inner("在同一个包内的类中");
	}
	
}
