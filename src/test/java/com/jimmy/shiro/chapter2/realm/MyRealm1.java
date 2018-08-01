package com.jimmy.shiro.chapter2.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * @description 用户名/密码类型的token校验
 * @author tjm_Jimmy
 *
 */
public class MyRealm1 implements Realm {
	
	private Logger logger = LoggerFactory.getLogger("MyRealm1");

	@Override
	public String getName() {
		
		return "MyRealm1";
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		
		return token instanceof UsernamePasswordToken;
	}

	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

		String userName = (String) token.getPrincipal();
		String password = new String((char[])token.getCredentials());
		
		logger.info("userName=" + userName + ";password=" + password);
		
		if(!"jimmy".equals(userName)){
			logger.error("用户名错误");
			throw new UnknownAccountException("用户名错误");
		}
		
		if(!"jimmy123456".equals(password)){
			logger.error("密码错误");
			throw new IncorrectCredentialsException("密码错误");
		}
		
		return new SimpleAuthenticationInfo(userName, password, getName());
	}

}
