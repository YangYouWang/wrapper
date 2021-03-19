package io.github.yangyouwang.annotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Warpper 注解类
 * @author yangyouwang
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Wrapper {
    /**
     * 字典键值对
     * @return 属性名称
     */
    String name() default "";
    /**
     * 字典键值对
     * @return 字段键值对 key:value
     */
    String[] dictData() default {};

    /**
     * 默认分隔符
     * @return 默认分隔符:
     */
    String separator() default ":";

    /**
     * 默认值
     * @return 未知
     */
    String def() default "未知";
}