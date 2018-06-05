package com.jimmy.chapter10;

public class Callbacks {

	public static void main(String[] args){

		Callee1 c1 = new Callee1();
		Callee2 c2 = new Callee2();
		MyIncrement.f(c2);
		Caller caller1 = new Caller(c1);
		Caller caller2 = new Caller(c2.getCallbackRef());
		caller1.go();
		caller1.go();
		caller2.go();
		caller2.go();
		caller1.go();
	}
	
}

interface Incrementable{
	void increment();
}

//直接实现接口
class Callee1 implements Incrementable{

	private int i;
	public void increment() {
		i++;
		System.out.println(i);		
	}	
}

class MyIncrement{
	public void increment(){
		System.out.println("other operation!");
	}
	static void f(MyIncrement mi){
		mi.increment();
	}
}

//用内部类的方式
class Callee2 extends MyIncrement{
	private int i;
	public void increment() {
		super.increment();
		i++;
		System.out.println(i);		
	}
	
	//内部类
	private class Closure implements Incrementable{

		public void increment() {
			//实际调用外围类的increment()方法
			Callee2.this.increment();
		}
		
	}
	
	Incrementable getCallbackRef(){
		return new Closure();
	}
	
}

//回调类
class Caller{
	private Incrementable callbackRef;
	Caller(Incrementable callbackRef){
		this.callbackRef = callbackRef;
	}
	void go(){
		callbackRef.increment();
	}
}