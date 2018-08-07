package com.jimmy.shiro.chapter2;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
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
		logger.info("Base64编码之后的字符串：" + encodeToString);
		String toString = Base64.decodeToString(encodeToString);
		logger.info("Base64解码之后的字符串：" + toString);
		
		logger.info("=========================================");
		String encodeHex = Hex.encodeToString(str.getBytes());
		logger.info("Hex编码之后的字符串：" + encodeHex);
		String sourceStr = new String(Hex.decode(encodeHex.getBytes()));
		logger.info("Hex解码之后的字符串：" + sourceStr);
	}
	
	@Test
	public void testCodec1(){
		String str = "hello world";
		String salt = "jimmy";
		
		String md5 = new Md5Hash(str, salt).toString();	//还可以转换为 toBase64()/toHex()
		logger.info("MD5加密之后的字符串：" + md5);
		
		String md52 = new Md5Hash(str, salt, 2).toString();	//指定散列次数，这里散列了2次
		logger.info("散列2次之后的字符串：" + md52);
				
		//使用SHA256算法生成相应的散列数据，另外还有如SHA1、SHA512算法
		String sha256 = new Sha256Hash(str, salt).toString();
		logger.info("Sha-1加密之后的字符串：" + sha256);
		
		String simpleHash = new SimpleHash("SHA-1", str, salt).toString();	//内部使用了Java的MessageDigest实现
		logger.info("SHA-1加密之后的字符串：" + simpleHash);
	}
	
}
