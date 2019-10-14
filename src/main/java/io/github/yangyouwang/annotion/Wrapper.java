package io.github.yangyouwang.annotion;

import io.github.yangyouwang.consts.ConfigConsts;

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
     * 字典类型
     * @return 字典类型值
     */
    String dictType() default ConfigConsts.WRAPPER_TYPE_ARRAY;
    /**
     * 字典键值对
     * @return 字段键值对 key:value
     */
    String[] dictData() default {};
}