package wrapper.annotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Warpper 注解类
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Warpper {
    /**
     * 字典类型
     * @return 字典类型值
     */
    String dictType() default "";
    /**
     * 字典键值对
     * @return 字段键值对 key:value
     */
    String[] dictData() default {};
}