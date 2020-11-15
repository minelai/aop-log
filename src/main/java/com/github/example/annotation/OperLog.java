package com.github.example.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义日志注解
 * @author: Mr.lai
 * @date: 2020/11/8 10:59
 */
@Target(ElementType.METHOD) // 注解放置目标：方法
@Retention(RetentionPolicy.RUNTIME) // 运行时执行
public @interface OperLog {

    // 操作模块
    String module() default "";

    // 操作名称
    String name() default "";

    // 操作说明
    String desc() default "";

}
