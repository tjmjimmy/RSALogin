package com.jimmy.chapter10;

public class Outer {

	public int i;
	
	public class Inner{
		
		public Inner(String s){
			System.out.println(s);
			System.out.println("i = " + i++);
		}
	}
	
	public Inner getInner(){
		return new Inner("Inner Contructor");
	}
	
	public void test(){
		Inner i = getInner();
		System.out.println("test()-" + i);
	}
	
	//嵌套类
	public static class InnerContent implements Contents{

		public int value() {
			return i + 1;
		}
		
	}
	
	public static void main(String[] args){
		Outer o = new Outer();
		Inner i = o.getInner();
		System.err.println("main()-" + i);
		//下面这种创建普通内部类的方式是错误的，普通内部类对象必须和一个外围类对象相关联
		//Inner i2 = new Inner("Inner2");
		//这才是正确的创建内部类的方式
		Inner i2 = o.new Inner("Inner2");
	}
}
