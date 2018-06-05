package com.jimmy.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jimmy.testSpring.StudentDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext-test.xml"})
public class StudentDaoTest extends AbstractJUnit4SpringContextTests {


 	@Autowired private StudentDao studentDao; 	
 	@Autowired private ApplicationContext ctx;
	

	@Test
	public void test(){
		studentDao.testAutowired();
	}
	
	@Test
	public void test1(){
		
		StudentDao dao = ctx.getBean(StudentDao.class);
		dao.testAutowired();
	}
}
