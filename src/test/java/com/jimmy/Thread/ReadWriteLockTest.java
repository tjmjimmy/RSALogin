package com.jimmy.Thread;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {
	
	/**
	 * ReadWriteLock分为读锁和写锁，横向是线程A，纵向是线程B，只有两个线程同时请求读锁时才不会造成阻塞，其它情况都会造成阻塞
	 * ***********************
	 * *	 * 读锁       * 写锁   *
	 * ***********************
	 * * 读锁 * 不阻塞   *	阻塞   *
	 * ***********************
	 * * 写锁 * 阻塞      *	阻塞	 *
	 * ***********************
	 * */
	ReadWriteLock lock = new ReentrantReadWriteLock();
	
	public synchronized void synchronizedRead(Thread thread){
		long start = System.currentTimeMillis();
		System.out.println("线程" + thread.getName() + "开始读操作……");
		while(System.currentTimeMillis() - start <= 1){
			System.out.println("线程" + thread.getName() + "正在读操作……");
		}
		System.out.println("线程" + thread.getName() + "读操作结束……");
	}
	
	public void readWriteLockRead(Thread thread){
		lock.readLock().lock();
		try {
			long start = System.currentTimeMillis();
			System.out.println("线程" + thread.getName() + "开始读操作……");
			while(System.currentTimeMillis() - start <= 1){
				System.out.println("线程" + thread.getName() + "正在读操作……");
			}
			System.out.println("线程" + thread.getName() + "读操作结束……");
		} finally{
			lock.readLock().unlock();
		}
	}
	
	public static void main(String[] args){
		ReadWriteLockTest test = new ReadWriteLockTest();
		
		new Thread("A"){
			@Override
			public void run(){
				test.synchronizedRead(Thread.currentThread());
			}
		}.start();
		new Thread("B"){
			@Override
			public void run(){
				test.synchronizedRead(Thread.currentThread());
			}
		}.start();
	}
}
