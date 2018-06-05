package com.jimmy.Thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

	private int sum = 0;
	//一定要将lock声明为成员变量，如果在方法中声明并创建lock，则不能起到锁的效果	
	Lock lock = new ReentrantLock();
	
	public void insert(Thread thread){
		lock.lock();
		try {
			System.out.println("线程" + thread.getName() + "获取了锁……");
			for(int i = 0; i < 10; i++){
				sum += i;
				System.out.println("线程" + thread.getName() + ">>>sum=" + sum);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			lock.unlock();
			System.out.println("线程" + thread.getName() + "释放了锁……");
		}
	}
	
	//测试无参的tryLock方法
	public void testTryLock(Thread thread){
		if(lock.tryLock()){
			try {
				System.out.println("线程" + thread.getName() + "拿到锁了……");
				for(int i = 0; i < 10; i++){
					sum += i;
					System.out.println("线程" + thread.getName() + ">>>sum=" + sum);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}finally{
				lock.unlock();
				System.out.println("线程" + thread.getName() + "释放了锁……");
			}
		}else{
			System.out.println("线程" + thread.getName() + "获取锁失败了……");
		}
	}

	//测试带参数的tryLock方法
	public void testTryLockArgs(Thread thread) throws InterruptedException{
		if(lock.tryLock(5, TimeUnit.SECONDS)){
			try {
				System.out.println("time=" + System.currentTimeMillis() + ",线程" + thread.getName() + "拿到锁了……");
				long now = System.currentTimeMillis();
				while(System.currentTimeMillis() - now < 6000){
					//空方法，耗时操作
					//Thread.sleep()方法也会抛出InterruptedException异常
				}
			} finally{
				lock.unlock();
				System.out.println("线程" + thread.getName() + "释放了锁……");
			}
		}else{
			System.out.println("线程" + thread.getName() + "放弃获取锁了……");
		}
	}
	
	public static void main(String[] args){
		
		LockTest test = new LockTest();
		/* 测试insert()方法和testTryLock(Threadthread)方法
		Thread threadA = new Thread("A"){
			@Override
			public void run(){
				//test.insert(Thread.currentThread());
				test.testTryLock(Thread.currentThread());
			}
		};
		
		Thread threadB = new Thread("B"){
			@Override
			public void run(){
				//test.insert(Thread.currentThread());
				test.testTryLock(Thread.currentThread());
			}
		};
		threadA.start();
		threadB.start();*/
		
		/*****************测试testTryLockArgs(Thread thread)方法*******************/
		Thread th1 = new Thread("AA"){
			@Override
			public void run(){
				try {
					test.testTryLockArgs(Thread.currentThread());
				} catch (InterruptedException e) {
					System.out.println("time=" + System.currentTimeMillis() + ",线程" + Thread.currentThread().getName() + "被中断了……");
				}
			}
		};
		Thread th2 = new Thread("BB"){
			@Override
			public void run(){
				try {
					test.testTryLockArgs(Thread.currentThread());
				} catch (InterruptedException e) {
					System.out.println("time=" + System.currentTimeMillis() + ",线程" + Thread.currentThread().getName() + "被中断了……");
				}
			}
		};
		th1.start();
		th2.start();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		th2.interrupt();
	}
}
