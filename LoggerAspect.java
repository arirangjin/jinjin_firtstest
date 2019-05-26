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
		//�Ű����� ���� �ڵ� => joinPoint.getArgs()[0]
		System.out.println("--------����--------->  " + signatureString);
		long start = System.currentTimeMillis();
		// log.info(name + type + "." + joinPoint.getSignature().getName() + "()");

		Object obj = null;
		try {
			obj = joinPoint.proceed(); // �ٽ� ��� ����

		} catch (Exception e) {
			System.out.println("�� �� �� �� �� �� �� �� ���ܹ߻�  : " + e.toString());
		} finally {
			long end = System.currentTimeMillis();
			System.out.println("--------����--------->  "+signatureString);
			System.out.println("���� �ð� : "+(end-start));
		}
		return obj;
		// return joinPoint.proceed();

	}
}