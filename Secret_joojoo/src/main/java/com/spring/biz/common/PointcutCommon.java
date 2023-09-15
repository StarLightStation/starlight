package com.spring.biz.common;

import org.aspectj.lang.annotation.Pointcut;

public class PointcutCommon {	//	PointCut 모듈화 클래스.
	
	@Pointcut("execution(* com.spring.biz..*Impl.*(..))")
	public void aPointcut() {}	//	AfterReturning
	
	@Pointcut("execution(* com.spring.biz..*Impl.*(..))")
	public void bPointcut() {}	//	AfterThrowing
	
	@Pointcut("execution(* com.spring.biz..*Impl.selectOne*(..))")
	public void cPointcut() {}	//	AfterReturning
	
	@Pointcut("execution(* com.spring.biz..*Impl.insert*(..))")
	public void dPointcut() {}	//	AfterReturning
	
	@Pointcut("execution(* com.spring.biz..*Impl.update*(..))")
	public void ePointcut() {}	//	AfterReturning
	
	@Pointcut("execution(* com.spring.biz..*Impl.delete*(..))")
	public void fPointcut() {}	//	AfterReturning
	
}	//	PointcutCommon
