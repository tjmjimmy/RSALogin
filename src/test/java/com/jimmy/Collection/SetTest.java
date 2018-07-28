package com.jimmy.Collection;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class SetTest {

	private Set<String> set = new TreeSet<String>();

	void initSet(){
		if(set.size() <= 0){
			set.add("asd");
			set.add("zhsr");
			set.add("kghdg");
			set.add("dfh");
			set.add("sbbs");
			set.add("adsrg");
			set.add("tryj");
			set.add("ofdgf");
		}
	}

	public Set<String> getSet() {
		return set;
	}

	public void setSet(Set<String> set) {
		this.set = set;
	}
	
	public static void main(String[] args) {
		
		SetTest test = new SetTest();
		test.initSet();
		
		Iterator<String> iterator = test.getSet().iterator();
		
		for(String s : test.getSet()){
			System.out.println(s);
		}
		
		System.out.println("-------------------------------");
		
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
		
	}
	
}
