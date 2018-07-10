package com.jimmy.Collection;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;

public class ArrayDequeTest {
	
	public static void main(String[] args) {
		
		Deque<String> deque =  new ArrayDeque<String>(Arrays.asList("a1", "ab2", "a3", "ab4", "a5", "ab6", "a7", "ab8", "a9"));
		System.out.println(deque.size());
		//在尾部加入新元素
		deque.add("asfgas");
		//倒序输出
		for(Iterator<String> itr = deque.descendingIterator();itr.hasNext();){
			System.out.println(itr.next());
		}
		System.out.println("deque size……" + deque.size());
		System.out.println("first element……" + deque.getFirst());
		//删除首部元素
		deque.pollFirst();
		System.out.println("first element……" + deque.getFirst());
		//在首部添加元素
		deque.push("gadfg");
		System.out.println("first element……" + deque.getFirst());
		System.out.println("last element……" + deque.getLast());
		//删除尾部元素
		deque.pollLast();
		System.out.println("last element……" + deque.getLast());
	}
}
