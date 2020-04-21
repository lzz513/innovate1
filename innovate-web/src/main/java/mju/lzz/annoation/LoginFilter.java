package mju.lzz.annoation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginFilter {
	public static final int NOT_LOGIN = 0;
	public static final int NEED_LOGIN = 1;
	public static final int ADMIN_LOGIN = 2;
	int value();
}
