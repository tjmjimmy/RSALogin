package com.jimmy.proxy;

import java.lang.reflect.Proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProxyTest {

	private static Logger logger = LoggerFactory.getLogger("ProxyTest");
	
	public static void main(String[] args) {
		//这一句是生成代理类的class文件，前提是你需要在工程根目录下创建com/sun/proxy目录，不然会报找不到路径的io异常
	    System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
		
		logger.info("开始proxy测试……");
		
		//被代理真实对象
	    MyBusinessImpl bpimpl = new MyBusinessImpl();
	    //被代理对象调用处理程序，需传入被代理对象
	    MyBusinessInvocationHandler handler = new MyBusinessInvocationHandler(bpimpl);
	    //生成代理类实例
	    MyBusinessInterface bp = (MyBusinessInterface)Proxy.newProxyInstance(bpimpl.getClass().getClassLoader(), bpimpl.getClass().getInterfaces(), handler);
	    //调用processBusiness
	    bp.processBusiness("ccmc");
	    
		logger.info("proxy测试完成……");
	}

}
