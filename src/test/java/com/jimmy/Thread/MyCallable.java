package com.jimmy.Thread;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

import com.jimmy.inherit.CollegeStudent;

public class MyCallable implements Callable<List<CollegeStudent>> {

	private int taskNum;
	
	MyCallable(int num){
		taskNum = num;
	}
	
	public List<CollegeStudent> call() throws Exception {
		// TODO Auto-generated method stub
		Date dateTmp1 = new Date();
//		Thread.sleep(500);
		
		List<CollegeStudent> list = new ArrayList<CollegeStudent>();
		for(int i = 0; i < 10; i++){
			CollegeStudent cs = new CollegeStudent("10" + i, "78" + i);
			list.add(cs);
		}
		
		Date dateTmp2 = new Date();
		
		long time = dateTmp2.getTime() - dateTmp1.getTime();
		System.out.println(taskNum + "任务返回结果，当前运行时间【" + time + "毫秒】");
		return list;
	}

	public int getTaskNum() {
		return taskNum;
	}

	public void setTaskNum(int taskNum) {
		this.taskNum = taskNum;
	}

}
