package com.jimmy.lock;

import java.util.concurrent.locks.ReentrantLock;


/**
 * @description 重入锁测试
 * @author tjm_Jimmy
 *
 */
public class ReentrantLockTest {

	private ReentrantLock lock = new ReentrantLock();
	
	int a = 0; 
	
	void writer(){
		lock.lock();
		try {
			a++;
		} catch (Exception e) {
			System.out.println(Thread.currentThread().getName() + e.getMessage());
		}finally {
			lock.unlock();
		}
	}
	
	void reader(){
		lock.lock();
		try {
			int i = a;
			System.out.println(Thread.currentThread().getName() + i);
		} catch (Exception e) {
			System.out.println(Thread.currentThread().getName() + e.getMessage());
		}finally {
			lock.unlock();
		}
	}
}
