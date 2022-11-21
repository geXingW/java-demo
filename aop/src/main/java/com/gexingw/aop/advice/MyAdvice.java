package com.gexingw.aop.advice;

import com.gexingw.common.util.JsonUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: GeXingW
 * @date: 2022/11/21
 * @time: 14:24
 */
@Aspect
@Component
public class MyAdvice {
    private Logger logger = LoggerFactory.getLogger(MyAdvice.class);

    /**
     * 定义切面
     */
    @Pointcut(value = "execution(* com.gexingw.aop.controller.*.*(..))")
    public void myPointcut() {

    }

    /**
     * 定义通知
     */
    @Around("myPointcut()")
    public Object myLogger(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取增强的类名
        String className = joinPoint.getTarget().getClass().toString();

        // 获取方法名
        String methodName = joinPoint.getSignature().getName();

        // 获取参数
        Object[] args = joinPoint.getArgs();

        logger.info("调用前：" + className + "：" + methodName + "，返回值：" + JsonUtil.asString(args));

        // 执行
        Object proceed = joinPoint.proceed();

        // 获取返回值
        logger.info("调用后：" + className + "：" + methodName + "，返回值：" + JsonUtil.asString(proceed));

        return proceed;
    }
}
