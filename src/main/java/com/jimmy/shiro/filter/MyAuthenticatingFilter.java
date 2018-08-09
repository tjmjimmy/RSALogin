package com.jimmy.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;

public class MyAuthenticatingFilter extends AuthenticatingFilter {

	@Override
	protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String username = (String) request.getAttribute("username");
        String password = (String) request.getAttribute("password");
        return createToken(username, password, request, response);
	}

	/**
	 * 表示访问拒绝时是否自己处理，
	 * 如果返回true表示自己不处理且继续拦截器链执行，
	 * 返回false表示自己已经处理了（比如重定向到另一个页面）
	 */
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return true;
	}

	
	/**
	 * 是否允许访问，返回true表示允许
	 */
	@Override  
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {  
        // 如果是登录操作，直接拦截不通过；shiro的实现是，如果用户登录过，再登录时，由于isAuthenticated为true，则直接不拦截，又跳转到登录页面去了。  
        // 修改后，如果是post登录操作，则会重新执行登录过程，刷新当前session中的用户信息  
        if (isLoginRequest(request, response)) {  
            return false;  
        } else {  
            return SecurityUtils.getSubject().isAuthenticated() || isPermissive(mappedValue);  
        }  
    }
	
}
