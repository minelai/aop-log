package com.github.example.aop;

import com.github.example.annotation.OperLog;
import com.github.example.utils.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author: Mr.lai
 * @date: 2020/11/8 11:01
 */
@Slf4j
@Aspect
@Component
public class OperLogAsper {


    /**
     * 设置操作日志切人点
     */
    @Pointcut("@annotation(com.github.example.annotation.OperLog)")
    public void operLogPoinCut() {

    }

    /**
     * 扫描module下所有controller包下的操作
     * 配置对应规则：com.github.example.modules.*.controller..*(..)
     */
    @Pointcut("execution(* com.github.example.modules.*.controller..*(..))")
    public void operExceptionLogPointCut() {
    }

    /**
     * 正常返回通知，拦截用户操作日志，连接点正常执行完成后执行， 如果连接点抛出异常，则不会执行
     *
     * @param joinPoint
     * @param keys
     */
    @AfterReturning(value = "operLogPoinCut()", returning = "keys")
    public void saveOperLog(JoinPoint joinPoint, Object keys) {
        // 获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes
                .resolveReference(RequestAttributes.REFERENCE_REQUEST);


        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 获取切入点方法
        Method method = signature.getMethod();
        // 注解配置
        OperLog annotation = method.getAnnotation(OperLog.class);

        if (ObjectUtils.isEmpty(annotation)) {
            return;
        }
        // 操作模块
        String module = annotation.module();
        // 操作名称
        String name = annotation.name();
        // 操作描述
        String desc = annotation.desc();
        // 请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        // 请求的方法
        String methodName = method.getName();
        // 参数
        String params = RequestUtil.getParams(request);

        // OTHER:IP、浏览器、系统

        log.info("类名：{} 方法：{} 模块：{} 名称：{} 描述：{} 参数：{} 结果：{}", className, methodName, module, name, desc, params, keys);
        //TODO 往数据库中记录操作日志
    }

    /**
     * 异常返回通知，用于拦截异常日志信息 连接点抛出异常后执行
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "operExceptionLogPointCut()", throwing = "e")
    public void saveExceptionLog(JoinPoint joinPoint, Throwable e) {
        // 获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes
                .resolveReference(RequestAttributes.REFERENCE_REQUEST);

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 方法名称
        Method methodName = signature.getMethod();
        // 类名称
        String className = joinPoint.getTarget().getClass().getName();

        // 异常名称
        String eName = e.getClass().getName();
        // 异常信息
        String eMessage = stackTraceToString(eName, e.getMessage(), e.getStackTrace());

        // OTHER:IP、浏览器、系统

        log.info("类名：{} 方法：{} 异常：{} 异常信息：{}", className, methodName, eName, eMessage);
        //TODO 往数据库中记录异常日志
    }

    /**
     * 转换异常信息为字符串
     *
     * @param exceptionName    异常名称
     * @param exceptionMessage 异常信息
     * @param elements         堆栈信息
     */
    public String stackTraceToString(String exceptionName, String exceptionMessage, StackTraceElement[] elements) {
        StringBuffer strbuff = new StringBuffer();
        for (StackTraceElement stet : elements) {
            strbuff.append(stet + "\n");
        }
        String message = exceptionName + ":" + exceptionMessage + "\n\t" + strbuff.toString();
        return message;
    }

}
