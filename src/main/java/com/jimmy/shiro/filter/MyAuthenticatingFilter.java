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

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

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
