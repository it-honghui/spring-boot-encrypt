package com.hcr.config.annotation;

import java.lang.annotation.*;

/**
 * 忽略解密注解
 * <p>加了此注解的接口将不进行数据解密操作<p>
 * <p>适用于全局开启加解密操作，但是想忽略某些接口场景<p>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DecryptIgnore {

    String value() default "";

}
