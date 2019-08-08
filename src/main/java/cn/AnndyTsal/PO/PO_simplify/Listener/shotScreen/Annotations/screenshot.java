package cn.AnndyTsal.PO.PO_simplify.Listener.shotScreen.Annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface screenshot  {

	// 是否截图 默认需要截图
	boolean isScreenshot() default true;
	
	//图片命名  默认为null
	String name() default "";
	
//	//截图类型
	String file();
}
