package com.jimmy.test;

public class Animal {
	
	public static int header = 1;

	static{
//		System.out.println("Animal class!");
		System.out.println("Animal class-" + header);
	}
	
	public void eat(){
		System.out.println("Animal eat food!");
	}
	
	public static void Shout(){
		System.out.println("Animal static method Shout()!");
	}
	
}
