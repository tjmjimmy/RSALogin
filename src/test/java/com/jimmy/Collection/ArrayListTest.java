package com.jimmy.Collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ArrayListTest {

	private Logger logger = LoggerFactory.getLogger(ArrayListTest.class);
	
	List<String> strList = new ArrayList<String>(Arrays.asList("a1", "ab2", "a3", "ab4", "a5", "ab6", "a7", "ab8", "a9"));
	
 	void printListIterator(){
		logger.info("开始测试ListIterator()方法");
		logger.debug("开始测试ListIterator()方法");
		logger.warn("开始测试ListIterator()方法");
		logger.error("开始测试ListIterator()方法");
		ListIterator<String> listIterator = strList.listIterator();
		while(listIterator.hasNext()){
			System.out.println(listIterator.next());
		}
		logger.info("ListIterator()方法测试结束");
	}
	
	public static void main(String[] args) {
		ArrayListTest test = new ArrayListTest();
		test.printListIterator();
	}
	
}
