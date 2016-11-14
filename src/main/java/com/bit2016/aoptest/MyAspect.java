package com.bit2016.aoptest;

import org.aspectj.lang.*;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.*;

@Aspect
@Component
public class MyAspect {
	
	@Before("execution(ProductVo com.bit2016.aoptest.ProductService.find(..))")
	public void beforeAdvice() {
		System.out.println("call [before advice]");
	}

	@After("execution(* *..*.ProductService.*(..))")
	public void afterAdvice() {
		System.out.println("call [after advice]");
	}
	
	@AfterReturning("execution(* *..*.ProductService.*(..))")
	public void afterReturningAdvice() {
		System.out.println("call [afterReturning advice]");
	}

	@AfterThrowing(value="execution(* *..*.ProductService.*(..))", throwing="ex")
	public void afterThrowingAdvice(Throwable ex) {
		System.out.println("call [afterThrowing advice : " + ex + "]");
	}
	
	@Around("execution(* *..*.ProductService.*(..))")
	public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
		// before
		System.out.println("call [around advice] : before");
		
		// point cut이 되는 메서드 호출
		Object[] parameters = {"camera"	};
		Object result = pjp.proceed(parameters);

		// after
		System.out.println("call [around advice] : after");
		
		return result;
	}
}
