package com.jimmy.Collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;

import com.jimmy.inherit.CollegeStudent;

public class CollectionTest {

	List<String> strList = new ArrayList<String>(Arrays.asList("a1", "ab2", "a3", "ab4", "a5", "ab6", "a7", "ab8", "a9"));
	
	List<Integer> intList = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5));
	
	Map<String, CollegeStudent> concurrentHashMap = new ConcurrentHashMap<String, CollegeStudent>();
	
	Map<String, CollegeStudent> hashMap = new HashMap<String, CollegeStudent>();
	
	Map<String, String> hashTable = new Hashtable<String, String>();
	
	<T> void print(Collection<T> list){
		for(T t : list){
			System.out.println(t);
		}
	}
	
	<K, V> void printMap(Map<K, V> map){
		for(K k: map.keySet()){
			System.out.println(Thread.currentThread().getName() + ":" + k + "==>" + map.get(k));
		}	
	}

	<K, V> void synchronizedPrintMap(Map<K, V> map){
		synchronized(map){
			for(K k: map.keySet()){
				System.out.println(Thread.currentThread().getName() + ":" + k + "==>" + map.get(k));
			}	
		}		
	}
	
	void initConcurrentHashMap(){
		if(concurrentHashMap.size() <= 0){
			concurrentHashMap.put("a", new CollegeStudent("1", "101"));
			concurrentHashMap.put("b", new CollegeStudent("2", "102"));
			concurrentHashMap.put("c", new CollegeStudent("3", "103"));
			concurrentHashMap.put("d", new CollegeStudent("4", "104"));
			concurrentHashMap.put("e", new CollegeStudent("5", "105"));
			concurrentHashMap.put("f", new CollegeStudent("6", "106"));
			concurrentHashMap.put("g", new CollegeStudent("7", "107"));
			concurrentHashMap.put("h", new CollegeStudent("8", "108"));			
		}
	}
	
	void initHashMap(){
		if(hashMap.size() <= 0){
			hashMap.put("aa", new CollegeStudent("112", "101"));
			hashMap.put("bb", new CollegeStudent("212", "102"));
			hashMap.put("cc", new CollegeStudent("312", "103"));
			hashMap.put("dd", new CollegeStudent("412", "104"));
			hashMap.put("ee", new CollegeStudent("512", "105"));
			hashMap.put("ff", new CollegeStudent("612", "106"));
			hashMap.put("gg", new CollegeStudent("712", "107"));
			hashMap.put("hh", new CollegeStudent("812", "108"));			
		}
	}
	
	/**
	 * 移除集合中指定值的元素，只能用迭代器Iterator
	 */
	@Test
	public void test1(){
		
		for(Iterator<String> ite = strList.iterator(); ite.hasNext();){
			String next = ite.next();
			if(next.contains("b")){
				ite.remove();
			}
		}
		
		print(strList);
	}
	
	/**
	 * 错误的方法
	 */
	@Test
	public void test2(){
		
		for(Iterator<String> ite = strList.iterator(); ite.hasNext();){
			String str = ite.next();
			if(str.contains("b")){
				strList.remove(str);
			}
		}
		
		print(strList);
	}
	
	/**
	 * 错误的方法
	 */
	@Test
	public void test3(){
		
		for(int i = 0; i < strList.size(); i++){
			String str = strList.get(i);
			if(str.contains("b")){
				strList.remove(str);
			}
		}
		
		print(strList);
	}

	@Test
	public void test4(){
		int size = intList.size();
		for(int i = 0; i < size; i++){
			intList.set(i, i + 10);
		}
		
		print(intList);
	}
	
	/**
	 * 这里用JUint测试很难看出效果，而且thread.sleep()方法会失效，所以使用main()方法
	 * */
	public static void main(String[] args){
		
		final CollectionTest test = new CollectionTest();
		
		test.initConcurrentHashMap();
		final Map<String, CollegeStudent> concurrentHashMap = test.getConcurrentHashMap();
		test.printMap(concurrentHashMap);
		
		Thread thread1 = new Thread(new Runnable() {
			
			public void run() {
				for(String key : concurrentHashMap.keySet()){
					CollegeStudent cs = concurrentHashMap.get(key);
					cs.setGrade(cs.getGrade() + "abcd");
					System.out.println("thread1" + ":" + key + "==>" + cs.toString());
				}
			}
		}, "thread1");
		
		Thread thread2 = new Thread(new Runnable() {
			
			public void run() {
				for(String key : concurrentHashMap.keySet()){
					CollegeStudent cs = concurrentHashMap.get(key);
					cs.setDormitory(cs.getDormitory() + "123456");
					System.out.println("thread2" + ":" + key + "==>" + cs.toString());
				}
			}
		}, "thread2");
	
		thread1.start();
		thread2.start();
		
	}
	
	/*
	public static void main(String[] args){
		
		final CollectionTest test = new CollectionTest();
		
		test.initHashMap();
		
		final Map<String, CollegeStudent> hashMap = test.getHashMap();
		
		test.printMap(hashMap);
		
		Thread thread1 = new Thread(new Runnable() {
			
			public void run() {
				for(String key : hashMap.keySet()){
					CollegeStudent cs = hashMap.get(key);
					cs.setGrade(cs.getGrade() + "abcd");
					System.out.println("thread1" + ":" + key + "==>" + cs.toString());
				}
			}
		}, "thread1");
		
		Thread thread2 = new Thread(new Runnable() {
			
			public void run() {
				for(String key : hashMap.keySet()){
					CollegeStudent cs = hashMap.get(key);
					cs.setDormitory(cs.getDormitory() + "123456");
					System.out.println("thread2" + ":" + key + "==>" + cs.toString());
				}
			}
		}, "thread2");
		
		thread1.start();
		thread2.start();
	}
*/
	public List<String> getStrList() {
		return strList;
	}

	public void setStrList(List<String> strList) {
		this.strList = strList;
	}

	public List<Integer> getIntList() {
		return intList;
	}

	public void setIntList(List<Integer> intList) {
		this.intList = intList;
	}

	public Map<String, CollegeStudent> getConcurrentHashMap() {
		return concurrentHashMap;
	}

	public void setConcurrentHashMap(Map<String, CollegeStudent> concurrentHashMap) {
		this.concurrentHashMap = concurrentHashMap;
	}

	public Map<String, CollegeStudent> getHashMap() {
		return hashMap;
	}

	public void setHashMap(Map<String, CollegeStudent> hashMap) {
		this.hashMap = hashMap;
	}
	
}
