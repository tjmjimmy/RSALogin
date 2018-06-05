package com.jimmy.Thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

	private int sum = 0;
	//一定要将lock声明为成员变量，如果在方法中声明并创建lock，则不能起到锁的效果	
	ReentrantLock lock = new ReentrantLock();
	
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
	
	//测试带参数的tryLock方法
	public void testLockInterruptibly(Thread thread) throws InterruptedException{
		//如果将lock.lockInterruptibly();放在try{}内，finally{}中的lock.unlock()则会报异常
		lock.lockInterruptibly();
		System.out.println("当前线程" + thread.getName() + "是否获取了锁：" + lock.isHeldByCurrentThread());
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
	}
		
	
	
	public static void main(String[] args){
		
		final LockTest test = new LockTest();
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
		threadB.start();	*/
		
		/*****************测试testTryLockArgs(Thread thread)方法*******************/
		/*
		MyThread th1 = new MyThread(test, "AA");
		MyThread th2 = new MyThread(test, "BB");
		th1.start();
		th2.start();
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		th2.interrupt();	*/
		
		/************不用第三方类封装测试testTryLockArgs(Thread thread)方法和testLockInterruptibly(Thread thread)方法***********/
		
		Thread threadA = new Thread("A"){
			@Override
			public void run(){
				try {
					test.testLockInterruptibly(Thread.currentThread());
				} catch (InterruptedException e) {
					System.err.println("time=" + System.currentTimeMillis() + ",线程" + Thread.currentThread().getName() + "被中断…………");
				}
			}
		};		
		Thread threadB = new Thread("B"){
			@Override
			public void run(){
				try {
					test.testLockInterruptibly(Thread.currentThread());
				} catch (InterruptedException e) {
					System.err.println("time=" + System.currentTimeMillis() + ",线程" + Thread.currentThread().getName() + "被中断…………");
				}
			}
		};
		threadA.start();
		threadB.start();
		
		System.out.println("当前线程名：" + Thread.currentThread().getName());
		
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		threadB.interrupt();	
	}
	
}

class MyThread extends Thread{
	
	private LockTest test = new LockTest();
	
	MyThread(LockTest test, String name){
		super(name);
		this.test = test;
	}
	
	@Override
	public void run(){
		try {
			test.testTryLockArgs(Thread.currentThread());
		} catch (InterruptedException e) {
			System.err.println("time=" + System.currentTimeMillis() + ",线程" + Thread.currentThread().getName() + "被中断…………");
		}
	}
}

