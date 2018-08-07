package com.jimmy.shiro.chapter2;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;

import com.google.common.hash.Hasher;

public class HashServiceTest {

	public static void main(String[] args) {
		DefaultHashService hashService = new DefaultHashService(); //默认算法SHA-512  
		hashService.setHashAlgorithmName("SHA-512");  
		hashService.setPrivateSalt(new SimpleByteSource("jimmy")); //私盐，默认无  
		hashService.setGeneratePublicSalt(true);//是否生成公盐，默认false  
		hashService.setRandomNumberGenerator(new SecureRandomNumberGenerator());//用于生成公盐。默认就这个  
		hashService.setHashIterations(1); //生成Hash值的迭代次数  
		  
		HashRequest defaultRequest = new HashRequest.Builder()
				.setSource(ByteSource.Util.bytes("hello"))
				.setIterations(2).build();
		String hexDefault = hashService.computeHash(defaultRequest).toHex();
		System.out.println("经过默认算法加密再hex编码之后的字符串-->" + hexDefault);
		
		HashRequest requestMD5 = new HashRequest.Builder()  
		            .setAlgorithmName("MD5").setSource(ByteSource.Util.bytes("hello"))  
		            .setSalt(ByteSource.Util.bytes("123")).setIterations(2).build();  
		String hexMD5 = hashService.computeHash(requestMD5).toHex();
		System.out.println("经过MD5加密再hex编码之后的字符串-->" + hexMD5);
	}
}
