package com.k15t.pat.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Aspect
@Component
public class MethodLoggerAspect {

    private Logger logger = LoggerFactory.getLogger(MethodLoggerAspect.class);

    @Around("execution(* com.k15t.pat.service.*.*(..))")
    public Object logServices(ProceedingJoinPoint point) throws Throwable {
        String methodName = point.getSignature().getName();
        Object[] args = point.getArgs();

        if (args.length > 0)
            logger.info("Entering {}({}).", methodName, args);

        else
            logger.info("Entering {}().", methodName);

        Object result = point.proceed();
        Signature signature = point.getSignature();
        Class returnType = ((MethodSignature) signature).getReturnType();

        if (!Objects.equals(returnType, Void.TYPE))
            logger.info("Exiting {}({}).", methodName, result);

        else
            logger.info("Exiting {}().", methodName);

        return result;
    }
}
