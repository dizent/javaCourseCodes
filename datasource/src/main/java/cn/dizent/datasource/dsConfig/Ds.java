package cn.dizent.datasource.dsConfig;

import java.lang.annotation.*;

/**
 * @Auther: 布谷
 * @Date: 2021/8/10 14:34
 * @Description:
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Ds {

    String dsName() default "";
}
