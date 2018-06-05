package com.jimmy.Thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadTest {
	
	static Logger logger = LoggerFactory.getLogger("ThreadTest");
	
	public static void main(String[] args) throws InterruptedException{
		
		logger.info("……程序开始运行……");
				
		Thread t1 = new Thread(new Runnable(){

			public void run() {
				// TODO Auto-generated method stub
				String s = "hello world!";
				for(int i = 0; i<10; i++){
					s += "hello world!";
					System.out.println("thread1>>>" + s);
				}
			}
			
		});
		
		Thread t2 = new Thread(new Runnable(){

			public void run() {
				// TODO Auto-generated method stub
				String s = "java thread!";
				for(int i = 0; i<10; i++){
					s += "java thread!";
					System.out.println("thread2>>>" + s);
				}
			}
			
		});
		
		t1.start();
		t2.start();
		/**
		 * wait()方法：暂停当前线程，等待其他线程调用notify()或者notifyAll()将当前线程唤醒
		 * */
//		t2.wait(1000);
//		t1.notifyAll();
	}
}
