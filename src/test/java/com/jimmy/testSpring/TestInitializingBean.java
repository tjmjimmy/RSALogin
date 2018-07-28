package com.jimmy.testSpring;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class TestInitializingBean implements InitializingBean{

	public void afterPropertiesSet() throws Exception {
		System.out.println("test InitializingBean!");
		
		System.out.println("bean实例化完成！");
	}

}
