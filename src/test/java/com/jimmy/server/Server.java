package com.jimmy.server;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jimmy.testSpring.StudentDao;

public class Server {

	@SuppressWarnings("resource")
	public static void main(String[] args){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:applicationContext-test.xml");
		StudentDao studentDao = ctx.getBean(StudentDao.class);
		studentDao.testAutowired();
		
	}
	
}
