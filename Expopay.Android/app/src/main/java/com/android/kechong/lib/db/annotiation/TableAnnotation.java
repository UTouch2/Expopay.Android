package com.android.kechong.lib.db.annotiation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
// 文档
@Retention(RetentionPolicy.RUNTIME)
// 在运行时可以获取
@Target({ ElementType.TYPE })
// 作用到类的域上面
@Inherited
public @interface TableAnnotation {
	public String tableName() default "";
}