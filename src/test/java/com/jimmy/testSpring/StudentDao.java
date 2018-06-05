package com.jimmy.testSpring;

import org.springframework.stereotype.Repository;

@Repository
public class StudentDao {

	public void testAutowired(){
		System.out.println("StudentDao class!");
	}
}
