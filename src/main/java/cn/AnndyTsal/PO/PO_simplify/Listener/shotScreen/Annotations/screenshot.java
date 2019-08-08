package cn.AnndyTsal.PO.PO_simplify.Listener.shotScreen.Annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface screenshot  {

	// 是否截图 默认需要截图
	public boolean isScreenshot() default true;
	
	//图片命名  默认为null
	public String name() default "";
	
	//截图自定义文件位置
	public String file() default "";
}
