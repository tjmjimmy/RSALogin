/**
 * 有返回值的多线程测试
 * */
package com.jimmy.Thread;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.jimmy.inherit.CollegeStudent;

public class CallableTest {

	public static void main(String[] args) throws InterruptedException, ExecutionException{
		System.out.println("……程序开始运行……");
		Date d1 = new Date();
		int taskSize = 5;
		ExecutorService pool = Executors.newFixedThreadPool(taskSize);
		
		for(int i = 0; i < taskSize; i++){
			MyCallable c = new MyCallable(i);
			Future<List<CollegeStudent>> future = pool.submit(c);
			List<CollegeStudent> list = future.get();
			if(list != null && !list.isEmpty()){
				for(CollegeStudent cs : list){
					System.out.println(">>>" + c.getTaskNum() + "：" + cs);
				}
			}
		}
		
		Date d2 = new Date();
		long time = d2.getTime() - d1.getTime();
		System.out.println("……程序运行结束……运行时间：【" + time + "毫秒】");
		
	}
	
}
