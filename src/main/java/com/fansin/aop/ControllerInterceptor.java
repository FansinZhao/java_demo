package com.fansin.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhaofeng on 17-4-1.
 */
@Aspect
@Component
public class ControllerInterceptor {


    /**
     * 定义拦截类
     * */
    @Pointcut("execution(* com.fansin.aop..*(..)) && @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void methodPointcut(){

    }


    @Before("methodPointcut()")
    public  void before(JoinPoint joinPoint){

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String name = method.getName();
        System.out.println("权限校验:调用方法 "+name);

        //验证权限

        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            System.out.println("a");
            if(arg instanceof HttpServletRequest){
                HttpServletRequest request = (HttpServletRequest) arg;
                if (request.getRequestURL().toString().contains("/a")){
                    System.out.println("权限成功");
                    //成功
                }else{
                        //失败
                    System.out.println("你确定通过权限了?!");
                    return;
                }
            }
        }
    }




    /**
     * 为上面的Pointcut具体实现
     * */
    @Around("methodPointcut()")
    public  void doService(ProceedingJoinPoint pjp){
        long begin = System.currentTimeMillis();
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        String name = method.getName();

        //按照顺序输出参数
        Set<Object> allParams = new LinkedHashSet<>();
        System.out.println("请求执行方法 "+name);

        //保存返回结果
        Object result = null;

        //获取拦截参数
        Object[] args = pjp.getArgs();
        for (Object arg : args) {

            //
            if(arg instanceof Map<?,?>){
                //保留参数
                allParams.add((Map<String,Object>)arg);
            }else if ( arg instanceof HttpServletRequest){
                HttpServletRequest request = (HttpServletRequest) arg;
                System.out.println("请求参数:"+request.getRequestURL()+request.getMethod());
                //把参数保存
                allParams.add(request.getParameterMap());
            }else{
                //
                allParams.add("其他参数!");
            }
        }
        try {
            //正常执行结果
            result = pjp.proceed();
        } catch (Throwable throwable) {
            result = "出息异常了!";
        }

        if (result != null){
            long end = System.currentTimeMillis();
            System.out.println("{}执行结果所用时间:{}"+method+(end - begin));
        }


    }

}
