package com.jimmy.obj;

//单例模式
public class Soup {

	private Soup(){
		System.out.println("this class'name is Soup");
	};
	
	private static Soup soup = new Soup();
	
	public static Soup getInstance(){
		return soup;
	}
}
