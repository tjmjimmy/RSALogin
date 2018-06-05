package com.jimmy.test;

import org.junit.Test;

public class TestThreadLocal {
	
	ThreadLocal<Long> longLocal = new ThreadLocal<Long>();

	ThreadLocal<String> stringLocal = new ThreadLocal<String>();
	
	public void set(){
		longLocal.set(Thread.currentThread().getId());
		stringLocal.set(Thread.currentThread().getName());
	}
	
	public Long getLong(){
		return longLocal.get();
	}
	
	public String getString(){
		return stringLocal.get();
	}
	
	@Test
	public void test() throws InterruptedException{
		final TestThreadLocal test = new TestThreadLocal();
		test.set();
		System.out.println(test.getLong());
		System.out.println(test.getString());
		
		Thread thread1 = new Thread("thread1"){
			public void run(){
				test.set();
				System.out.println(test.getLong());
				System.out.println(test.getString());
			}
		};
		thread1.start();
		thread1.join();
		
		System.out.println(test.getLong());
		System.out.println(test.getString());
	}
	
}
