package com.jimmy.proxy;

public class MyBusinessImpl implements MyBusinessInterface {

	@Override
	public void processBusiness(String name) {
		
		System.out.println("processing budiness…………" + name);
	}

}
