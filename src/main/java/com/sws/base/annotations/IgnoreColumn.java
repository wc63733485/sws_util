package com.sws.base.annotations;

import java.lang.annotation.*;

/**
 * 忽视列-注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface IgnoreColumn {

    /**
     * 列名
     * @return 列名
     */
    String value() default "";

    /**
     * 是否可以为空
     * @return {@code true} 可以
     */
    boolean nullable() default true;

    /**
     * 字段的长度
     * @return 字段的长度
     */
    int length() default 255;

}