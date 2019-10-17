package com.coolance;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName ServerApi
 * @Description 服务器相关信息
 * @Author Coolance
 * @Version
 * @Date 2019/10/16 11:22
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ServerApi {

    String value() default "";
}
