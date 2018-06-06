package com.jimmy.Thread;

public class SynchronizedTest {
	/**
	 * 理解synchronized关键字，首先要理解锁的概念，who lock who？？？锁加在那个对象上？类锁还是对象锁？
	 * */
	public static void main(String[] args){
		
		Print p = new Print();
		
		Thread th1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				p.printArray();
			}
		}, "thread1");
		Thread th2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				p.synchronizedPrint();
			}
		}, "thread2");
		
		th1.start();
		th2.start();
	}	

}

class Print{
	Object o = new Object();
	void printArray(){
		/**
		 * 在synchronized()中new Object()并不能实现synchronized效果，因为在不同线程中调用该方法，synchronized()锁住的是不同的对象，都是新new出来的
		 * synchronized()中必须是成员变量
		 * 将下行该为synchronized(o)，输出结果就不一样
		 * */
		/**
		 * 在new tmp()构造方法中，虽然使用了静态成员变量，但synchronized(new tmp())加的是tmp的对象锁，不是tmp类锁，所以也不会造成阻塞
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
	
	synchronized void synchronizedPrint(){
		int sum = 0;
		for(int i = 0; i < 100; i++){
			sum += i;
			System.out.println(Thread.currentThread().getName() + ">>>sum=" + sum);
		}
	}
	
}

class tmp{
	static int i = 0;
	tmp(){
		System.out.println(Thread.currentThread().getName() + ">>>" + this.hashCode() + ">>i=" + i);
	}
}