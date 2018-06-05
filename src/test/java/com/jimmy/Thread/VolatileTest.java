package com.jimmy.Thread;

public class VolatileTest {

	private volatile int inc = 0;
	
	void increment(){
		inc++;
	}
	
	synchronized void synchronizedIncrement(){
		inc++;
	}
	
	public static void main(String[] args){
		
		VolatileTest test = new VolatileTest();
		
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
		while(System.currentTimeMillis() - start < 20*1000){
			
		}
		System.out.println("inc=" + test.getInc());
		
		/*
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
		*/
	}

	public int getInc() {
		return inc;
	}

	public void setInc(int inc) {
		this.inc = inc;
	}
	
}
