package com.bugframework.common.utility;
import java.util.Locale;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public final class SpringContextUtil implements ApplicationContextAware{
	private static ApplicationContext context;

	  public void setApplicationContext(ApplicationContext paramApplicationContext)
	    throws BeansException
	  {
	    context = paramApplicationContext;
	  }

	  public static Object getBean(String paramString)
	  {
	    return context.getBean(paramString);
	  }

	  public static String getMessage(String paramString)
	  {
	    return context.getMessage(paramString, null, Locale.getDefault());
	  }
}
