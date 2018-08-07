package com.jimmy.shiro.chapter2;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LoginLogoutTest {
	
	private static Logger logger = LoggerFactory.getLogger("LoginLogoutTest");

	@Test
	public void testLogin(){
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini"); 
		
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("jimmy", "jimmy123456");
		
		try {
			logger.info("开始校验");
			subject.login(token);
		} catch (AuthenticationException e) {
			logger.error(e.getMessage(), e);
		}
		
		Assert.assertEquals(true, subject.isAuthenticated());
		logger.info("校验成功");
		subject.logout();
	}
	
	@Test
	public void testRealm(){
		
		logger.info("testRealm()方法开始");
		
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini"); 
		
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("jimmy", "jimmy123456");
		
		try {
			logger.info("开始校验");
			subject.login(token);
		} catch (AuthenticationException e) {
			logger.error(e.getMessage(), e);
		}
		
		Assert.assertEquals(true, subject.isAuthenticated());
		logger.info("校验成功");
		subject.logout();
	}
	
	@Test
	public void testCodec(){
		String str = "hello world";
		String encodeToString = Base64.encodeToString(str.getBytes());
		logger.info("Base64加密之后的字符串" + encodeToString);
		String toString = Base64.decodeToString(encodeToString);
		logger.info("Base64解密之后的字符串" + toString);
		
		logger.info("=========================================");
		String encodeHex = Hex.encodeToString(str.getBytes());
		logger.info("Hex加密之后的字符串" + encodeHex);
		String sourceStr = new String(Hex.decode(encodeHex.getBytes()));
		logger.info("Hex解密之后的字符串" + sourceStr);
	}
}
