package com.jimmy.service;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.google.common.util.concurrent.RateLimiter;

/**
 * @author tjm_Jimmy
 *
 */
@Service
@Scope("singleton")
public class RateLimitService {

	private RateLimiter limiter = RateLimiter.create(10.0, 20, TimeUnit.SECONDS);
		
	/**
	 * 获取令牌，当前无令牌时会阻塞直到获取令牌
	 * @return
	 */
	public synchronized double acquire(){
		return limiter.acquire();
	}
	
	/**
	 * 获取令牌，无令牌时立即返回false
	 * @return
	 */
	public synchronized boolean tryAcquire(){
		return limiter.tryAcquire();
	}
}
