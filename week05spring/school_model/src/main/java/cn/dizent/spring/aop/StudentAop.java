package cn.dizent.spring.aop;


import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @Auther: 布谷
 * @Date: 2021/7/25 09:26
 * @Description:
 */
public class StudentAop {

    public void around(ProceedingJoinPoint joinpoint) throws Throwable {

        System.out.println("student arount prefix // 1");

        joinpoint.proceed();

        System.out.println("student arount suffix // 2");

    }
}
