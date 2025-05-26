package com.niu.mall.security.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解，有该注解的缓存方法会抛出异常
 *
 * 并不是所有的方法都需要处理异常的，比如我们的验证码存储，如果我们的Redis宕机了，我们的验证码存储接口需要的是报错，而不是返回执行成功。
 * 我们可以通过自定义注解来完成，首先我们自定义一个CacheException注解，如果方法上面有这个注解，发生异常则直接抛出。
 * Created by lihaojie on 2023/3/17.
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheException {
}
