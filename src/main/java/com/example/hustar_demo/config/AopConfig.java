package com.example.hustar_demo.config;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Log4j2
public class AopConfig {

    @Around("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public Object postLogging(ProceedingJoinPoint pjp) throws Throwable {
        return logging(pjp);
    }

    @Around("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public Object getLogging(ProceedingJoinPoint pjp) throws Throwable {
        return logging(pjp);
    }


    public Object logging(ProceedingJoinPoint pjp) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        long startAt = System.currentTimeMillis();

        String reqParam = getRequestParm(pjp);

        if(reqParam.equals("")){
            log.info("-----------> REQUEST : [{}][{}] {}({})", request.getRequestURI(), request.getRemoteAddr(), pjp.getSignature().getDeclaringTypeName(),
                    pjp.getSignature().getName());
        }else{
            log.info("-----------> REQUEST : [{}][{}] {}({}) = {}", request.getRequestURI(), request.getRemoteAddr(), pjp.getSignature().getDeclaringTypeName(),
                    pjp.getSignature().getName(), reqParam);
        }

        Object result = pjp.proceed();

        long endAt = System.currentTimeMillis();

        log.info("-----------> RESPONSE : [{}][{}] {}({}) = {} ({}ms)"
                , request.getRequestURI(), request.getRemoteAddr(), pjp.getSignature().getDeclaringTypeName(),pjp.getSignature().getName()
                , result , endAt-startAt);

        return result;
    }


    public String getRequestParm(ProceedingJoinPoint pjp){
        String argStr = "";

        if(pjp.getArgs().length > 0){
            if(pjp.getArgs()[0] == null){
                argStr = "null";
            }else{
                argStr = pjp.getArgs()[0].toString();
            }
        }

        return argStr;
    }
}
