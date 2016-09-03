package com.bugframework.common.utility;


 
import javax.servlet.http.HttpServletRequest;

public class ResourceUtil
{
  public static String getRequestPath(HttpServletRequest request)
  {
    String requestPath = request.getRequestURI() + "?" + request.getQueryString();
    if (requestPath.indexOf("&") > -1) {
      requestPath = requestPath.substring(0, requestPath.indexOf("&"));
    }
    requestPath = requestPath.substring(request.getContextPath().length() + 1);
    return requestPath;
  }
 
  public static String getSysPath()
  {
    String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
    String temp = path.replaceFirst("file:/", "").replaceFirst("WEB-INF/classes/", "");
    String separator = System.getProperty("file.separator");
    String resultPath = temp.replaceAll("/", separator + separator).replaceAll("%20", " ");
    return resultPath;
  }

  public static String getPorjectPath()
  {
    String nowpath = System.getProperty("user.dir");
    String tempdir = nowpath.replace("bin", "webapps");
    tempdir = tempdir + "\\";
    return tempdir;
  }

  public static String getClassPath() {
    String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
    String temp = path.replaceFirst("file:/", "");
    String separator = System.getProperty("file.separator");
    String resultPath = temp.replaceAll("/", separator + separator);
    return resultPath;
  }

  public static String getSystempPath() {
    return System.getProperty("java.io.tmpdir");
  }

  public static String getSeparator() {
    return System.getProperty("file.separator");
  }

  public static String getParameter(String field) {
    HttpServletRequest request = ContextHolderUtils.getRequest();
    return request.getParameter(field);
  }
 
}