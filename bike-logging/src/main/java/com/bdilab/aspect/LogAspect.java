package com.bdilab.aspect;

import com.bdilab.entity.Log;
import com.bdilab.service.LogService;
import com.bdilab.service.impl.LogServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
public class LogAspect {

    @Autowired
    private LogServiceImpl logService;
    private ThreadLocal<Long> currentTime = new ThreadLocal<>();

    //切入点
    @Pointcut(value = "@annotation(com.bdilab.aop.Log)")
    public void logPointCut() {
    }

    //环绕通知
    @Around(value = "logPointCut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        currentTime.set(System.currentTimeMillis());
        Object res = joinPoint.proceed();
        //请求耗时
        long time = System.currentTimeMillis() - currentTime.get();
        currentTime.remove();
        Log log = new Log();
        log.setLogType("info");
        //日志记录耗时
        log.setTime(time);
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        String methodName = joinPoint.getTarget().getClass().getName() + "." + signature.getName() + "()";
        //日志记录方法名
        log.setMethod(methodName);
        //获取参数名称
        String[] paraNames = signature.getParameterNames();
        //获取参数值
        Object[] args = joinPoint.getArgs();
        StringBuffer sb = new StringBuffer("{");
        for (int i = 0; i < paraNames.length; i++) {
            sb.append(paraNames[i]+": "+args[i]);
            if (i<paraNames.length-1) sb.append(", ");
        }
        sb.append("}");
        //日志记录参数信息
        log.setArgs(sb.toString());
        String desc = signature.getMethod().getAnnotation(com.bdilab.aop.Log.class).value();
        //日志记录描述信息
        log.setDescription(desc);
        logService.save(log);
        return res;
    }

    //记录异常信息
    @AfterThrowing(pointcut = "logPointCut()",throwing = "e" )
    public void logAfterThrowing(JoinPoint point, Throwable e) {
        Log log = new Log();
        log.setLogType("error");
        log.setTime(System.currentTimeMillis() - currentTime.get());
        currentTime.remove();
        log.setExceptionDetail(e.getMessage());
        logService.save(log);
    }
}
