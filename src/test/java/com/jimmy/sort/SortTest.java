package com.jimmy.sort;

import java.util.Collections;
import java.util.Random;
import java.util.Vector;

import org.junit.Test;

public class SortTest {

	public void FindTwoElement(Vector<Integer> vec, int sum){
		
		//严格来说这里要校验入参，暂时忽略
		//排序
		Collections.sort(vec);
		for(int j = 0; j < vec.size(); j++){
			System.out.println(j + "-->" + vec.get(j));
		}
		
		int begin = 0, end = vec.size() - 1;
		int flag = 0;//自增自减标志，0--begin,end都不改变，1--begin自增，2--end自减
		while(begin < end){
			int tmp = vec.get(begin) + vec.get(end);
			if(tmp == sum){
				System.out.println("begin=" + begin + ",end=" + end);
				if(flag == 1){
					++begin;
				}else if(flag == 2){
					--end;
				}
			}else if(tmp < sum){
				++begin;
				flag = 1;
			}else{ 
				--end;
				flag = 2;
			}
		}
	}
	
	@Test
	public void test(){
		Random ran = new Random(20);
		Vector<Integer> vec = new Vector<Integer>();
		for(int i = 0;i < 20; i++){
			vec.add(ran.nextInt(20));
		}
		int sum = 10;
		FindTwoElement(vec, sum);
	}
}
