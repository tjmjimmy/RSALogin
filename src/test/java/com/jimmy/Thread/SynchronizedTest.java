package com.jimmy.Thread;

public class SynchronizedTest {
	/**
	 * 理解synchronized关键字，首先要理解锁的概念，who lock who？？？锁加在那个对象上？类锁还是对象锁？
	 * */
	public static void main(String[] args){
		
		Print p1 = new Print();
		Print p2 = new Print();
		
		SynClazz sc1 = new SynClazz();
		SynClazz sc2 = new SynClazz();
		/*
		Thread th1 = new Thread("thread1"){
			
			@Override
			public void run() {
				p1.printArray();
			}
		};
		Thread th2 = new Thread("thread2"){
			
			@Override
			public void run() {
				p2.synchronizedPrint();
			}
		};
		Thread th3 = new Thread("thread3"){
			@Override
			public void run() {
				Print.staicSynchronizedPrint();
			}
		};
		*/		
		/*Thread th4 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				sc1.print();
				
			}
		}, "thread4");
		
		Thread th5 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				sc2.print();
				
			}
		}, "thread5");*/
		
		/*Thread th6 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				p1.synClazzPrintArgs(sc1);				
			}
		}, "thread6");*/

		Thread th7 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				SynClazz.statiIincrement();				
			}
		}, "thread7");

		Thread th8 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				sc1.increment();
			}
		}, "thread8");
		
//		th1.start();
//		th2.start();
//		th3.start();		
//		th4.start();
//		th5.start();
//		th6.start();
		th7.start();
		th8.start();
	}	

}

class Print{
	private Object o = new Object();
	
	void ordinaryPrint(){
		int sum = 0;
		for(int i = 0; i < 100; i++){
			sum += i;
			System.out.println(Thread.currentThread().getName() + ">>>sum=" + sum);
		}
	}
	
	void printArray(){
		/**
		 * 在synchronized()中new Object()并不能实现synchronized效果，因为在不同线程中调用该方法，synchronized()锁住的是不同的对象，都是新new出来的
		 * synchronized()中必须是成员变量
		 * 将下行该为synchronized(o)，输出结果就不一样
		 * */
		/**
		 * 在new temp()构造方法中，虽然使用了静态成员变量，但synchronized(new temp())加的是temp的对象锁，不是temp类锁，所以也不会造成阻塞
		 * */
		/**
		 * 如果synchronized(o)，再用不同线程执行printArray()和synchronizedPrint()也不会实现synchronized的效果，
		 * 因为printArray()方法锁住的是o对象，synchronizedPrint()方法锁住的是this对象
		 * */
		synchronized(this){
			int sum = 0;
			for(int i = 0; i < 100; i++){
				sum += i;
				System.out.println(Thread.currentThread().getName() + ">>>sum=" + sum);
			}
		}		
	}
	
	void synClazzPrint(){
		synchronized (SynClazz.class) {
			int sum = 0;
			for(int i = 0; i < 100; i++){
				sum += i;
				System.out.println(Thread.currentThread().getName() + ">>>sum=" + sum);
			}
		}
	}

	void synClazzPrintArgs(SynClazz sc){
		synchronized (SynClazz.class) {
			sc.increment();
		}
	}
	
	void synClazzPrintArgs(){
		synchronized (SynClazz.class) {
			SynClazz.statiIincrement();
		}
	}
	
	
	/**
	 * synchronized方法占用的是对象锁，哪个对象调用该方法锁住的就是哪个对象，
	 * 和synchronized（this）一样，占用的都是对象锁
	 * */
	synchronized void synchronizedPrint(){
		int sum = 0;
		for(int i = 0; i < 100; i++){
			sum += i;
			System.out.println(Thread.currentThread().getName() + ">>>sum=" + sum);
		}
	}
	
	/**
	 * static synchronized修饰的方法占用的是类锁
	 * */
	static synchronized void staicSynchronizedPrint(){
		int sum = 0;
		for(int i = 0; i < 100; i++){
			sum += i;
			System.out.println(Thread.currentThread().getName() + ">>>sum=" + sum);
		}
	}
	
}

class SynClazz {

	private Print p = new Print();
	
	void print(){
		p.synClazzPrint();
	}
	
	void increment(){
		synchronized (this) {
			int sum = 0;
			for(int i = 0; i < 50; i++){
				sum += i;
				System.out.println(Thread.currentThread().getName() + ">>>sum=" + sum);
			}
			
		}
	}

	static void statiIincrement(){
		synchronized (SynClazz.class) {
			int sum = 0;
			for(int i = 0; i < 50; i++){
				sum += i;
				System.out.println(Thread.currentThread().getName() + ">>>sum=" + sum);
			}
			
		}
	}
}

class temp{
	static int i = 0;
	temp(){
		System.out.println(Thread.currentThread().getName() + ">>>" + this.hashCode() + ">>i=" + i);
	}
}
