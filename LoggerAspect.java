package com.project1.example1;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class LoggerAspect {

	public static final Log log = LogFactory.getLog(LoggerAspect.class);
	static String name = "";
	static String type = "";
	static String whatmethod = "";

	// * com.. or execution(* first..service.*Impl.*(..)) or execution(*
	// first..dao.*DAO.*(..))
	@Around("execution(* com.project1.example1.dbconnect..*.*(..))")
	public Object logPrint(ProceedingJoinPoint joinPoint) throws Throwable {
		type = joinPoint.getSignature().getDeclaringTypeName();
		String signatureString = joinPoint.getSignature().toShortString();
		//매개변수 보는 코드 => joinPoint.getArgs()[0]
		System.out.println("--------시작--------->  " + signatureString);
		long start = System.currentTimeMillis();
		// log.info(name + type + "." + joinPoint.getSignature().getName() + "()");

		Object obj = null;
		try {
			obj = joinPoint.proceed(); // 핵심 기능 실행

		} catch (Exception e) {
			System.out.println("♬ ♬ ♬ ♬ ♬ ♬ ♬ ♬ 예외발생  : " + e.toString());
		} finally {
			long end = System.currentTimeMillis();
			System.out.println("--------종료--------->  "+signatureString);
			System.out.println("수행 시간 : "+(end-start));
		}
		return obj;
		// return joinPoint.proceed();test

	}
}