package com.jimmy.controller;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.jimmy.utils.RSAUtils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Scope("prototype")
@Controller
public class LoginController {
	
	Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping("/login")
	public String login(Model model, HttpServletRequest request, HttpServletResponse response){
		// 将公钥的 modulus 和 exponent 传给页面。
		// Hex -> apache commons-codec
		RSAPublicKey publicKey = RSAUtils.getDefaultPublicKey();//默认公钥
		// 产生公私钥对
		KeyPair keyPair = RSAUtils.getKeyPair();
		PublicKey pubKey = keyPair.getPublic();
		PrivateKey priKey = keyPair.getPrivate();
		
        model.addAttribute("modulus", new String(Hex.encodeHex(publicKey.getModulus().toByteArray())));
        model.addAttribute("exponent", new String(Hex.encodeHex(publicKey.getPublicExponent().toByteArray())));
		
        model.addAttribute("contextPath", request.getContextPath());
        
        Cookie c = new Cookie("lc", "testCookie");
        c.setMaxAge(-1);
        response.addCookie(c);
        
		return "login";
	}
	
	@RequestMapping("/ajaxLogin")
	public void ajaxLogin(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		JSONObject json = new JSONObject();
		String psd = request.getParameter("password");
		String decryptpsd = RSAUtils.decryptStringByJs(psd);
		if("1234qwer".equals(decryptpsd)){
			PrintWriter writer = response.getWriter();
			json.put("code", "200");
			writer.write(json.toJSONString());
			writer.flush();
			writer.close();
		}
	}
	
	@RequestMapping("/testCookie")
	public void getCookie(HttpServletRequest request, HttpServletResponse response){
		
		Cookie[] cookies = request.getCookies();
		String requestURI = request.getRequestURI();
		if(request.getParameter("mydata") instanceof String){
			logger.info("hahaha" + request.getParameter("mydata"));
		}
		logger.info("requestURI--"+requestURI);
		for(Cookie c : cookies){
			if("lc".equals(c.getName())){
				logger.info("lc cookie："+c.getName() + "--"+ c.getValue());
			}
		}
	}
	
	@RequestMapping("/testUrl")
	public void testURL(HttpServletRequest request, HttpServletResponse response) throws MalformedURLException{
		String userDirPath = System.getProperty("user.dir");
		String javaClassPath = System.getProperty("java.class.path");
		URL url = new URL("http", "http://java.sun.com/j2se/1.3/", "../../../demo/jfc/SwingSet2/src/SwingSet2.java");
		File file = new File(javaClassPath, "../../../../WEB-INF/jsp/login.jsp");
		logger.info("userDirPath——" + userDirPath);
		logger.info("javaClassPath——" + javaClassPath);
		logger.info("url——" + url.getPath());
		logger.info("file的绝对路径——" + file.getAbsolutePath());
	}
	
}
