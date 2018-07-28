package com.jimmy.Collection;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

import com.jimmy.inherit.CollegeStudent;

public class TreeMapTest {

	public static void main(String[] args) {

		Map<String, CollegeStudent> treeMap = new TreeMap<String, CollegeStudent>(new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});

		//乱序添加
		treeMap.put("bb", new CollegeStudent("212", "102"));
		treeMap.put("gg", new CollegeStudent("712", "107"));
		treeMap.put("aa", new CollegeStudent("112", "101"));
		treeMap.put("cc", new CollegeStudent("312", "103"));
		treeMap.put("ee", new CollegeStudent("512", "105"));
		treeMap.put("hh", new CollegeStudent("812", "108"));
		treeMap.put("dd", new CollegeStudent("412", "104"));
		treeMap.put("ff", new CollegeStudent("612", "106"));	
		
		//顺序输出
		for(String s : treeMap.keySet()){
			System.out.println("key=" + s + "...value=" + treeMap.get(s));
		}
		
		treeMap.remove("ee");
		System.out.println("----------------------------------------------");
		
		for(String s : treeMap.keySet()){
			System.out.println("key=" + s + "...value=" + treeMap.get(s));
		}
		
	}
	
}
