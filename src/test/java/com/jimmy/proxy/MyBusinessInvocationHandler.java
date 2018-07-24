package com.jimmy.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyBusinessInvocationHandler implements InvocationHandler {
	
	private Object target;
	
	MyBusinessInvocationHandler(Object object){
		this.target = object;
	}
	 
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
	    
		System.out.println("You can do something here before process your business");
		// 调用目标对象的方法
		Object result = method.invoke(target, args);
		System.out.println("You can do something here after process your business");
		// 返回处理结果
		return result;
	}

}
