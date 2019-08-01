package com.hcr.config.annotation;

import java.lang.annotation.*;

/**
 * 解密注解
 * <p>加了此注解的接口将进行数据解密操作<p>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Decrypt {

    String value() default "";

}
