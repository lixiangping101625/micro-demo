package com.hlkj.webcomponents.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceLogAspect {
    /**
     * AOP通知主要有5种
     * 1. 前置通知@Before：方法调用前执行
     * 2. 后置通知@AfterReturning：方法调用后执行
     * 3. 环绕通知@Around：方法调用前后通知
     * 4. 异常通知@AfterThrowing：方法发生异常通知
     * 5. 最终通知@After：最终final通知，不管是否异常都会通知
     */

    private static final Logger logger =
            LoggerFactory.getLogger(ServiceLogAspect.class);

    /**
     * AOP切面表达式
     * execution 代表所要执行的表达式主体
     * 第一处 * 代表方法返回类型 *代表所有类型
     * 第二处 包名代表aop监控的类所在的包
     * 第三处 .. 代表该包以及其子包下的所有类方法
     * 第四处 * 代表类名，*代表所有类
     * 第五处 *(..) *代表类中的方法名，(..)表示方法中的任何参数
     */
    @Around("execution(* com.hlkj..*.service.impl..*.*(..))")
    public Object recordServiceTimeLog(ProceedingJoinPoint joinPoint) throws Throwable {

        logger.info("====== 开始执行{}.{} ======",
                joinPoint.getTarget().getClass(), // service 实现类
                joinPoint.getSignature().getName());  // 方法签名
        long begin = System.currentTimeMillis();

        // 执行目标方法
        Object result = joinPoint.proceed();

        long end = System.currentTimeMillis();
        logger.warn("====== 执行结束，耗时{}毫秒 ======", (end - begin));

        return result;
    }
}
