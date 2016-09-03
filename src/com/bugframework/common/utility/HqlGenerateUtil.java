package com.bugframework.common.utility;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Transient;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;

import com.bugframework.auth.pojo.AuthUser;
import com.bugframework.common.pojo.AjaxJson;
import com.bugframework.common.pojo.DataGrid;

 


public class HqlGenerateUtil {
	
	private static final String SUFFIX_COMMA = ",";
	private static final String SUFFIX_KG = " ";
	/**模糊查询符号*/
	private static final String SUFFIX_ASTERISK = "*";
	//--update-begin--Author:coco  Date:20130520 for：模糊查询
	private static final String SUFFIX_ASTERISK_VAGUE = "%";
	//--update-begin--Author:coco  Date:20130520 for：模糊查询
	/**不等于查询符号*/
	private static final String SUFFIX_NOT_EQUAL = "!";
	private static final String SUFFIX_NOT_EQUAL_NULL = "!NULL";
	
	/**时间查询符号*/
	private static final String END = "end";
	private static final String BEGIN = "begin";
	private static final Logger logger = Logger.getLogger(HqlGenerateUtil.class);
	

	
	/**
  	 * 自动生成查询条件HQL
  	 * 模糊查询
  	 * 【只对Integer类型和String类型的字段自动生成查询条件】
  	 * @param hql
  	 * @param values
  	 * @param searchObj
  	 * @throws Exception
  	 */
  	public static void installHql(Criteria cq,Object searchObj){
  		PropertyDescriptor origDescriptors[] = PropertyUtils.getPropertyDescriptors(searchObj);
  		// 获得对象属性中的name 
  		List<String> legalFiled = getSearchColumn(searchObj);
        for (int i = 0; i < origDescriptors.length; i++) {
            String name = origDescriptors[i].getName();//属性名称
            String type = origDescriptors[i].getPropertyType().toString();//属性类型
            String getName =origDescriptors[i].getReadMethod().getName();//属性的getName()方法名称
            boolean isLegal =false;
            if ("class".equals(name)||"ids".equals(name)||"page".equals(name)
            		||"rows".equals(name)||"sort".equals(name)||"order".equals(name)) {
                continue; // No point in trying to set an object's class
            }
            try {
            	
            outter:for(int j=0;j<legalFiled.size();j++){
            	if(legalFiled.get(j).equals(getName)){
            		isLegal =true;
            		legalFiled.remove(j);
            		break outter;
            	}
            }	
            if (PropertyUtils.isWriteable(searchObj, name)&&isLegal) {
               if("class java.lang.String".equals(type)){
            	   Object value = PropertyUtils.getSimpleProperty(searchObj, name);
            	   String searchValue = null;
            	   if(value!=null){
            		    searchValue = value.toString().trim();
            	   }else{
            		   continue;
            	   }
            	   cq.add(Expression.like(name, "%"+searchValue+"%"));
            	  
               }else if("class java.lang.Integer".equals(type)){
            	   Object value = PropertyUtils.getSimpleProperty(searchObj, name);
            	   if(value!=null&&!"".equals(value)){
            		   cq.add(Expression.eq(name, value));
            	   }
               } else if("class java.math.BigDecimal".equals(type)){
            	   //update-begin--Author:zhaojunfu  Date:20130503 for：增加对bigDecimal数据的支持
            	   Object value = PropertyUtils.getSimpleProperty(searchObj, name);
            	   if(value!=null&&!"".equals(value)){
            		   cq.add(Expression.eq(name, value));
            	   }
            	   //update-end--Author:zhaojunfu  Date:20130503 for：增加对bigDecimal数据的支持
               }else if("class java.lang.Short".equals(type)){
            	   //update-begin--Author:zhaojunfu  Date:20130518 for：TASK #93 增加对SHORT数据的支持
            	   Object value = PropertyUtils.getSimpleProperty(searchObj, name);
            	   if(value!=null&&!"".equals(value)){
            		   cq.add(Expression.eq(name, value));
            	   }
            	   //update-end--Author:zhaojunfu  Date:20130518 for：TASK #93增加对SHORT数据的支持
               }else if("class java.lang.Long".equals(type)){
            	   //update-begin--Author:zhaojunfu  Date:20130518 for：TASK #93 增加对LONG 数据的支持
            	   Object value = PropertyUtils.getSimpleProperty(searchObj, name);
            	   if(value!=null&&!"".equals(value)){
            		   cq.add(Expression.eq(name, value));
            	   }
            	   //update-end--Author:zhaojunfu  Date:20130518 for：TASK #93 增加对LONG 数据的支持
               }else if ("class java.util.Date".equals(type)) {
					Date value = (Date) PropertyUtils.getSimpleProperty(searchObj, name);
					if (null != value) {
						// 判断开始时间
						  cq.add(Expression.eq(name, value));
					 }
               }
            }
            } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
     
  	}
  	
	/**
	 * 得到对象属性中所有name
	 * @param origDescriptors
	 * @return
	 */
  	private static String getDescriptorsNames(PropertyDescriptor origDescriptors[]) {
  		StringBuilder sb = new StringBuilder();
  		for (int i = 0; i < origDescriptors.length; i++) {
  			sb.append(origDescriptors[i].getName() + ",");
  		}
  		return sb.toString();
  	}

	public static AjaxJson datagrid(Object datagrid) {
		AjaxJson j = new AjaxJson();
		j.setObj(datagrid);
		j.setSuccess(true);
		return j;
	}
	
	public static boolean isSearch(String name){
		return false;
	}
	public static List<String> getSearchColumn(Object obj){
		List<String> list =new ArrayList<String>();
		 Class pojo =obj.getClass();
		 Method []methodList = pojo.getMethods();
		 for(int j = 0;j < methodList.length;j++)
		   {
			 	Method method = methodList[j];
			 	 boolean methodAnnotation = method.isAnnotationPresent(Column.class);
			 	 if(methodAnnotation){
			 		list.add(method.getName()); 
			 	 }
		   }
		 return list;
	}
	public static void main(String[] args) {
	
		/*AuthUser user = new AuthUser();
		 Class pojo =user.getClass();
		 Field []fieldList = pojo.getDeclaredFields();
		 Method []methodList = pojo.getMethods();
		 for(int j = 0;j < methodList.length;j++)
		   {
			 	Method method = methodList[j];
			 	 boolean methodAnnotation = method.isAnnotationPresent(Transient.class);
			 	 if(methodAnnotation){
			 		   System.out.println(method.getName()); 
			 	 }
		   }*/	
		AuthUser user = new AuthUser();
		PropertyDescriptor origDescriptors[] = PropertyUtils.getPropertyDescriptors(user);
  		// 获得对象属性中的name 
  		
        for (int i = 0; i < origDescriptors.length; i++) {
            String name = origDescriptors[i].getName();
            System.out.println(origDescriptors[i].getReadMethod().getName());
        }
	}
  
}


