package com.jimmy.Thread;

public class Thread1 extends Thread {

	public Thread1(String name){
		super(name);
	}
	
	@Override
	public void run(){
		for(int i = 0;i<50;i++){
			System.out.println(getName() + ">>>" + i);
		}
	}
	
	public static void main(String[] args) throws InterruptedException{
		new Thread1("new thread").start();
		
		for(int i=0; i<100;i++){
			if(i == 50){
				Thread t1 = new Thread1("thread1");
				t1.start();
				/**
				 * join()方法：使其他线程等待当前线程终止
				 * */
				t1.join();
			}
			System.out.println(Thread.currentThread().getName() + ">>>" + i);
		}
	}
}
