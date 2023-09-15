package com.spring.biz.common;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class LogAdvice {	//	LogAdvice 모듈화 클래스.
	
	@AfterReturning(pointcut = "PointcutCommon.aPointcut()", returning = "returnObj")
	public void afterReturningLog(JoinPoint jp, Object returnObj) {	//	비즈니스 메서드 수행 후에 뜨는 로그.
		
		String getSignature = jp.getSignature().toString();
		int index = getSignature.indexOf("biz.") + 4;	//	biz. 으로 찾고, biz. 뒤의 인덱스 저장.
		
		if(index != -1) {
			String str = getSignature.substring(index);	//	biz. 뒤의 인덱스 뒤의 문자열을 저장.
			String[] arr = str.split("\\.");	//	. 을 기준으로 배열에 저장.
			
			if(arr.length > 2) {
				String log = arr[1] + "." + arr[2];
				System.out.println("log : " + log + "\n");	//	예) XxxService.CRUD(args)
			}
		}
		
	}	//	afterReturningLog
	
	@AfterThrowing(pointcut = "PointcutCommon.bPointcut()", throwing = "exceptObj")
	public void afterThrowingLog(JoinPoint jp, Exception exceptObj) {	//	비즈니스 메서드 수행 중에 예외 발생시 뜨는 로그.
		
		String getSignature = jp.getSignature().toString();
		int index = getSignature.indexOf("biz.") + 4;	//	biz. 으로 찾고, biz. 뒤의 인덱스 저장.
		
		if(index != -1) {
			String str = getSignature.substring(index);	//	biz. 뒤의 인덱스 뒤의 문자열을 저장.
			String[] arr = str.split("\\.");	//	. 을 기준으로 배열에 저장.
			
			if(arr.length > 2) {
				String log = arr[1] + "." + arr[2];
				System.out.println("log : " + log + "\n");	//	예) XxxService.CRUD(args)
				System.out.println("예외 : " + exceptObj.getMessage() + "\n");	//	예외 메세지
			}
		}
		
	}	//	afterThrowingLog
	
	@AfterReturning(pointcut = "PointcutCommon.cPointcut()", returning = "returnObj")
	public void afterReturningLogSelectOne(JoinPoint jp, Object returnObj) {
		
		System.out.println(Arrays.toString(jp.getArgs()) + "\n");
		
	}	//	afterReturningLogSelectOne
	
	@AfterReturning(pointcut = "PointcutCommon.dPointcut()", returning = "returnObj")
	public void afterReturningLogInsert(JoinPoint jp, Object returnObj) {
		
		System.out.println(Arrays.toString(jp.getArgs()) + "\n");
		
	}	//	afterReturningLogInsert
	
	@AfterReturning(pointcut = "PointcutCommon.ePointcut()", returning = "returnObj")
	public void afterReturningLogUpdate(JoinPoint jp, Object returnObj) {
		
		System.out.println(Arrays.toString(jp.getArgs()) + "\n");
		
	}	//	afterReturningLogUpdate
	
	@AfterReturning(pointcut = "PointcutCommon.fPointcut()", returning = "returnObj")
	public void afterReturningLogDelete(JoinPoint jp, Object returnObj) {
		
		System.out.println(Arrays.toString(jp.getArgs()) + "\n");
		
	}	//	afterReturningLogDelete

}	//	LogAdvice
