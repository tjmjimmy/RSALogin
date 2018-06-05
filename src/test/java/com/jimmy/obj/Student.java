package com.jimmy.obj;

public class Student implements Person {

	//实现接口内的方法只能是public权限
	public void sleep() {

	}

	//在类中嵌套定义接口，访问权限可以是private，protected，缺省，public
	private interface Games{
		
	}
	
	class Chess implements Games{
		
	}
	
}
