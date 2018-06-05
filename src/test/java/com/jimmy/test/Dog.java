package com.jimmy.test;

import org.junit.Test;

public class Dog extends Animal {

	static{
		header = 2;	//这个header变量是继承了Animal
//		System.out.println("Dog class!");
		System.out.println("Dog class-" + header);
	}
	
	public static void main(String[] args){
		/*
		Animal animal = new Dog();
		animal.eat();		
		*/
		Dog d = new Dog();
		d.eat();
	}

	@Override
	public void eat(){
		System.out.println("Dog eat meat!");
		sleep();
	}
	
	public void sleep(){
		System.out.println("Dog sleep!");
	}
	
	public static void Shout(){
		System.out.println("Dog static method Shout()!");
	}
	
	@Test
	public void testStatic(){

		Animal.Shout();
		//当Dog类中没有Shout()方法时，会调用父类的static Shout()方法，
		//但是搞不明白这在内存中时如何实现子类可以调用父类的static方法的
		Dog.Shout();
	}
	
}
