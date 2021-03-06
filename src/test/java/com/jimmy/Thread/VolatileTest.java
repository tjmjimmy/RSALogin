package com.jimmy.Thread;

public class VolatileTest {

	private volatile int inc = 0;
	
	void increment(){
		/**
		 * 这里其实有三个原子操作
		 * 1、从主内存获取inc的值复制到当前线程的本地内存
		 * 2、修改inc的值
		 * 3、将修改后的inc的值更新到主内存
		 * */		
		inc++;
		
		//拆成三步
		/*int temp = getInc();
		temp++;
		setInc(temp);
		*/
	}
	
	synchronized void synchronizedIncrement(){
		inc++;
	}
	
	public static void main(String[] args){
		
		VolatileTest test = new VolatileTest();
		/**
		 * 虽然inc用了volatile关键字修饰，但是下面这段代码输出却不是10000，因为volatile只能保证操作的可见性，却不能保证操作的原子性，
		 * 当两个线程同时获取inc的值时，线程A先获取了inc的值，但还没有进行操作，这时线程B也获取inc的值，则线程A，B获取到的值是一样的，然后都进行自加操作，
		 * 表面上对inc进行了两次操作，但实际效果只自加了1次 。
		 * */
		/*
		for(int num = 0; num < 10; num++){
			new Thread(){
				public void run(){
					for(int i = 0; i < 1000; i++){
						test.increment();
					}
				}
			}.start();
		}
		long start = System.currentTimeMillis();
		while(System.currentTimeMillis() - start < 60*1000){
			
		}
		System.out.println("inc=" + test.getInc());
		*/
		
		/**
		 * synchronized能保证操作的原子性，间接保证操作的可见性，因为同一时刻只有一个线程操作共享变量
		 * */
		for(int num = 0; num < 10; num++){
			new Thread(){
				public void run(){
					for(int i = 0; i < 1000; i++){
						test.synchronizedIncrement();
					}
				}
			}.start();
		}
		long start = System.currentTimeMillis();
		while(System.currentTimeMillis() - start < 10*1000){
			
		}
		System.out.println("inc=" + test.getInc());
		
	}

	public int getInc() {
		return inc;
	}

	public void setInc(int inc) {
		this.inc = inc;
	}
	
}
