package com.bugframework.common.tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;



import com.bugframework.auth.dao.RoleDao;
import com.bugframework.auth.pojo.AuthRole;
import com.bugframework.common.utility.DataUtils;
import com.bugframework.common.utility.SpringContextUtil;

public class DataGridTag  extends TagSupport{

	private String name;//表格ID，和名称
	private String actionUrl;//提交的路径
	private boolean	   pagination =true;//是否分页
	private String  width;//表格宽度
	private boolean checkbox =false;//是否显示复选框
	private String  style ="tablelist";//表格样式：普通列表tablelist（默认）,图片列表imgtable
	private String  onLoadSuccess;//数据加载成调用方法
	private String  onDblClick;//双击调用方法
	private String  onClick;//单击调用方法
	private String queryMode="single";//查询模式：single(单条件查询：默认),group(组合查询)
	private String keywordField;
	
	private List<DataGridColumn> datacDataGridColumns = new ArrayList<DataGridColumn>();//列数组
	private List<DataGridButton> czBtnList = new ArrayList<DataGridButton>();//操作里面的按钮操作
	private List<DataGridButton> toolBars = new ArrayList<DataGridButton>();//表头的按钮操作
	private List<String> fields = new ArrayList<String>();//存放参数名称
	
	private List<DataGridColumn> queryFields = new ArrayList<DataGridColumn>();//存放需要查询的列
	
	private short colspan = 0;//显示的列数
	
	//public RoleDao service = (RoleDao)SpringContextUtil.getBean("systemService");
	
	public void setName(String name) {
		this.name = name;
	}
	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}
	public void setPagination(boolean pagination) {
		this.pagination = pagination;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public void setCheckbox(boolean checkbox) {
		this.checkbox = checkbox;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public void setOnLoadSuccess(String onLoadSuccess) {
		this.onLoadSuccess = onLoadSuccess;
	}
	public void setOnDblClick(String onDblClick) {
		this.onDblClick = onDblClick;
	}
	public void setOnClick(String onClick) {
		this.onClick = onClick;
	}
	public void setQueryMode(String queryMode) {
		this.queryMode = queryMode;
	}
	
	public void setKeywordField(String keywordField) {
		this.keywordField = keywordField;
	}
	
	public String setKeyWorkName(){
		for(DataGridColumn c:this.queryFields){
			return c.getTitle();
		}
		if(this.queryFields.isEmpty()){
			return "[keywordField输入不正确或者为空]";
		}
		return null;
	}
	/**
	 * 存放操作里面的按钮
	 * @param czBtnList
	 */
	public void setCzBtn(DataGridButton czBtnList) {
		this.czBtnList.add(czBtnList);
	}
	
	/**
	 * 存放表格的列的各种参数
	 * @param column
	 */
	public void setDataGridColumns(DataGridColumn column){
		fields.add(column.getField());
		if(this.queryMode.equals("group")){//组合查询
			if(column.isQuery()){
				queryFields.add(column);
			}
		}
		if(this.queryMode.equals("single")){//关键字查询
			if(column.getField().equals(this.keywordField)){
				queryFields.add(column);
			}
		}
		if(column.isHidden()==false){
			colspan++;
		}
		datacDataGridColumns.add(column);
	} 
	
	
	/**
	 * 存放表头的按钮
	 * @param button
	 */
	public void setToolBar(DataGridButton button) {
		toolBars.add(button);
		
	}
	/*****开始执行方法***/
	@Override
	public int doStartTag() throws JspException {
		datacDataGridColumns.clear();
		czBtnList.clear();
		fields.clear();
		toolBars.clear();
		queryFields.clear();
		colspan=0;
		return EVAL_PAGE;
	}
	/*******结束执行方法**************/
	@Override
	public int doEndTag() throws JspException {
		
		try {
			setTableHead();//设置表头
			setTableValue();//设置表值
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}
	/**
	 * 设置表的顶部按钮
	 * @throws IOException
	 */
	public StringBuffer setTableButton()  throws IOException{
		StringBuffer sb = new StringBuffer();
		sb.append("<div class=\"tools\" id=\""+this.name+"_toolbar\">");
		sb.append("<ul class=\"toolbar\">");
		for(DataGridButton db:this.toolBars){
			String icon =db.getIcon();
			for(int i =0;i<DataGridToolBarTag.ICON_TYPE_NAME.length;i++){
				if(DataGridToolBarTag.ICON_TYPE_NAME[i].equals(icon)){
					icon = DataGridToolBarTag.ICON_TYPE_VALUE[i];
				}
			}
			icon =basePath()+icon;
			String name = TagUtil.getFunction(db.getFunname());
			String parmars = TagUtil.getFunParams(db.getFunname());
			sb.append("<li class=\"click\" onclick=" + name + "(" + parmars + ") ><span><img src=\""+icon+"\" /></span>"+db.getTitle()+"</li>");
		}
		sb.append("</ul>");
		sb.append(setSearch()); 
		sb.append("</div>");
		return sb;
	}
	
	public StringBuffer setSearch() throws IOException{
		StringBuffer sb = new StringBuffer();
		sb.append("<ul class=\"seachform\" id=\""+this.name+"_seachform\"  style=\"float: right;\">");
		if(this.queryMode.equals("single")){
			sb.append(" <li><label>"+setKeyWorkName()+"</label><input name='"+this.keywordField+"' type='text' class='scinput "+this.queryMode+"Class' /></li>");
		}
		if(this.queryMode.equals("group")){
			for(DataGridColumn column:this.queryFields){
				if(DataUtils.isStrNotEmpty(column.getReplace())){
					sb.append(" <li><label>"+column.getTitle()+"</label>");
					sb.append("<select name='"+column.getField()+"' class='"+this.queryMode+"Class' style=\"height:34px;border-top:solid 1px #a7b5bc; border-left:solid 1px #a7b5bc; border-right:solid 1px #ced9df; border-bottom:solid 1px #ced9df;\">");
						sb.append("<option value=''>请输入"+column.getTitle()+"</option>");    
					String[] selectarray = column.getReplace().split(",");
					    for(int i =0;i<selectarray.length;i++){
					    	sb.append("<option value='"+selectarray[i].split("_")[0]+"'>"+selectarray[i].split("_")[1]+"</option>");
					    }
					sb.append("</select></li>");
				}else if(DataUtils.isStrNotEmpty(column.getFormatter())){
					sb.append(" <li><label>"+column.getTitle()+"</label><input name='"+column.getField()+"' type='text' onfocus='WdatePicker({dateFmt:\""+column.getFormatter()+"\"})' class='Wdate scinput "+this.queryMode+"Class' /></li>");
				}else{
					sb.append(" <li><label>"+column.getTitle()+"</label><input name='"+column.getField()+"' type='text' class='scinput "+this.queryMode+"Class' /></li>");
				}
			}
		}
    	sb.append("<li style=\" margin-right:0;\"><label>&nbsp;</label><input name='' type=\"button\" class=\"scbtn\" onclick='doSearch()' value=\"查询\"/></li>");
        sb.append("</ul>");
        return sb;
	}
	/**
	 * 设计表头
	 * @throws IOException
	 */
	public void setTableHead() throws IOException{
		JspWriter out = this.pageContext.getOut();
		StringBuffer sb = new StringBuffer();
		sb.append("<div class=\"rightinfo\" id=\""+this.name+"\" action=\""+this.actionUrl+"\">");
		
		sb.append(setTableButton());//设置表的顶部按钮
		
		sb.append("<table class=\""+this.style+"\" id=\""+this.name+"_table\">");
		sb.append("<thead>");
		sb.append("<tr>");
		if(this.checkbox){
		sb.append("<th style=\"width:35px;\"><input id=\""+this.name+"_checkall\" type=\"checkbox\" value=\"\" /></th>");
		}
		for(DataGridColumn column:this.datacDataGridColumns){
			if(column.getField().equals("opt")){
				sb.append(" <th  style=\"text-align:center\" width="+column.getWidth()+">操作</th>");
			}else{
				String ishidden ="";
				if(column.isHidden()){
					ishidden =" style=\"display:none\"";
				}
				sb.append("<th "+ishidden+"  width="+column.getWidth()+" style=\"text-align:center\"  >"+column.getTitle()+"</th>");
			}
		}
		sb.append("</tr>");
		sb.append(" </thead>");
		sb.append("  <tbody>");
		sb.append("  </tbody>");
		sb.append("  </table>");
		sb.append("	</div>");
		out.print(sb.toString()); 
		out.flush(); 
		 
	}
	/**
	 * 设置表的值
	 */
	public void setTableValue() throws IOException{
	//	 List<AuthRole> role= service.getRoleList();
		 JspWriter out = this.pageContext.getOut();
		StringBuffer sb = new StringBuffer();
		sb.append("<script type='text/javascript'>");
		sb.append("var queryMode ='"+this.queryMode+"' ;");
		sb.append("var searchParams  ={};");//定义搜索列名称
		
		for(DataGridColumn c:this.datacDataGridColumns){
			sb.append("searchParams['"+c.getField()+"']=null;");
		}
		sb.append("searchParams['pageNo']=1;");//设置分页的初始值
		
		
		//回车登录
		sb.append("$('.seachform').keydown(function(e){");
				sb.append("if(e.keyCode == 13) {");
						sb.append("	doSearch();");
				sb.append("}");
		sb.append("});");
		
		sb.append("$(function(){");
		sb.append("	$(\"#"+this.name+"_checkall\").click(function() { ");
		sb.append("var flag = $(this).attr(\"checked\"); ");
		sb.append("$(\"input[name="+this.name+"_checkbox]:checkbox\").each(function() {");
		sb.append("if(flag==undefined){ $(this).removeAttr(\"checked\");}else{$(this).attr(\"checked\", flag);} ");
		sb.append("}) ");
		sb.append("}) ");
		sb.append("});");
		
		
		sb.append("function roadDataGrid(){");
		sb.append("var url = $('#"+this.name+"').attr('action');");
		sb.append("var getdata = {};");
	 	sb.append(" getdata =getSearchParams(searchParams);");
	//	sb.append(" url = url+getSearchParams(searchParams);");
		sb.append("$.ajax({");
		sb.append("type:'get',");
		sb.append("url:url,");
		sb.append("data:getdata,");
		sb.append("datatype:'json', ");
		sb.append("contentType: \"application/x-www-form-urlencoded; charset=utf-8\",");
		sb.append("async:false,");
		sb.append("success:function(data){");
		sb.append("if(data.success==true){");
			sb.append("var flag =0;");
			sb.append("var items =data.obj.datas;");
			sb.append("$('#"+this.name+" tbody').html('');");//清空旧数据
			if(this.pagination){
				sb.append("$('#"+this.name+"_pagin').html('');");//清空分页栏目
			}
			sb.append("var field ;");
			sb.append("var ishide ;");
			sb.append("var html='';");
			sb.append("$.each(items,function(i,value){");
			
				sb.append("flag=1;");
				sb.append("html+='<tr>';");
				if(this.checkbox){
					sb.append("html+='<td><input name=\""+this.name+"_checkbox\" type=\"checkbox\" value=\"'+value.id+'\"   /></td>';");
				}
				for(DataGridColumn column:this.datacDataGridColumns){
					 if(column.isImage()){//是图片
						 
						 sb.append("html+=\"<td style='imgtd' align="+column.getAlign()+"><img title='点击预览大图' onclick=viewByUrl('"+basePath()+"\"+value."+column.getField()+"+\"',630,360) style='width:"+column.getImgWidth()+"px;height:"+column.getImgHeight()+"px;' src='"+basePath()+"\"+value."+column.getField()+"+\"' /></td>\";");
					 }else if(column.getField().equals("opt")){//操作
						sb.append("var czhtml='';");
						for(DataGridButton button:czBtnList){
							String name = TagUtil.getFunction(button.getFunname());
							String parmars = TagUtil.getFunParams(button.getFunname());
							if(DataUtils.isStrNotEmpty(button.getExp())){
								String[] exps= button.getExp().split("#");
								String exps_Start = exps[0];
								String exps_Middle=exps[1].replace("eq", "==").replace("ne", "!=");
								String exps_End=exps[2].replace("empty", "");
								if(DataUtils.isNumeric(exps_End)){
									sb.append(" if(value."+exps_Start+exps_Middle+exps_End+" ){");
								}else{
									sb.append(" if(value."+exps_Start+exps_Middle+"\""+exps_End+"\" ){");
								}
								
								 sb.append("czhtml+=\"<a href=\'#\' onclick=" + name + "(" + button.setValueReplace(parmars, fields) + ") class='tablelink'>"+button.getTitle()+"</a>&nbsp;&nbsp;\";");
								
								 sb.append("}");
							 }else{
								 sb.append("czhtml+=\"<a href=\'#\' onclick=" + name + "(" + button.setValueReplace(parmars, fields) + ") class='tablelink'>"+button.getTitle()+"</a>&nbsp;&nbsp;\";");
							 }
							
						//	sb.append("czhtml +='<a href=\"javascript:"+button.getFunnameS()+"\\'"+button.setValueReplace(button.getFunnameM(), fields)+"\\' "+button.getFunnameE()+"\"  class=\"tablelink\">"+button.getTitle()+"</a>&nbsp;&nbsp;';");
						}
						sb.append("html+='<td>'+czhtml+'</td>';");//存放操作按钮
					}else {
						sb.append(" field =value."+column.getField()+";if(field==null){field=''}");
						if(DataUtils.isStrNotEmpty(column.getReplace())){
							sb.append(" field =replaceField(field,'"+column.getReplace()+"');");	
						}
						sb.append(" ishide ='';");
						if(column.isHidden()){
							//sb.append(" ishide ='display:none';");
						}else{
							if(DataUtils.isStrNotEmpty(column.getFormatter())){
								
								sb.append("if(!isNaN(field)){field =  new Date().format(\""+column.getFormatter()+"\",field);}");
							}
							sb.append("html+='<td style=\"'+ishide+'\" align="+column.getAlign()+">'+field+'</td>';");
						}
					}
				}
				
		       sb.append("html+='</tr>';");
		       if(DataUtils.isStrNotEmpty(this.onLoadSuccess)){
		       sb.append(this.onLoadSuccess);
		       }
			sb.append("});");
		
			sb.append("$('#"+this.name+" tbody').append(html);");
			sb.append(" if(flag==0){");
			if(this.checkbox){//如果是拥有选择列的话，这里要加一
				this.colspan++;
			}
		    sb.append(" $('#"+this.name+" tbody').append('<tr ><td colspan=\""+this.colspan+"\" align=\"center\">暂无数据！</td></tr>');");
		    sb.append("}");
		    
		    if(this.pagination){
		    /*****分页部分 start*******/
		    sb.append("searchParams['pageNo']=data.obj.pageNo;");//设置当前页面
		    sb.append("$(\"#"+this.name+"_pagin\").remove();");
		    sb.append("html='<div class=\"pagin\" id=\""+this.name+"_pagin\">';");
		    sb.append("html+='<div class=\"message\">共<i class=\"blue\">'+data.obj.recordCount+'</i>条记录，当前显示第&nbsp;<i class=\"blue\" >'+data.obj.pageNo+'&nbsp;</i>页</div>';");
		    sb.append("html+='<ul class=\"paginList\">';");
		    sb.append("if(data.obj.pageNo==1){");//如果是第一页的话，不给跳转
		    sb.append("  html+='<li class=\"paginItem\"><a href=\"javascript:void(0);\"><span class=\"pagepre\"></span></a></li>';");	
		    sb.append("}else{");	
		    sb.append("html+='<li class=\"paginItem\"><a href=\"javascript:setPageNow('+(data.obj.pageNo-1)+');\"><span class=\"pagepre\"></span></a></li>';");
		    sb.append("}");
		    sb.append(" var pagelong =2;");//翻页栏显示个数，超过个数着‘...’显示
		    sb.append(" var n =1;");
		    sb.append("  while(data.obj.pageNo>pagelong*n){");
		    sb.append("      n++; ");
		    sb.append("  }");
		    sb.append("var starno = (n-1)*pagelong+1;");
		    sb.append("for(var i=starno-1;i<(pagelong*n<data.obj.pageCount?pagelong*n:data.obj.pageCount);i++){");
		    sb.append("if(i+1==data.obj.pageNo){");
		    sb.append("html+='<li class=\"paginItem current\"><a href=\"javascript:void(0);\">'+(i+1)+'</a></li>';");
		    sb.append("}else{");
		    sb.append("	html+='<li class=\"paginItem\" ><a href=\"javascript:setPageNow('+(i+1)+');\">'+(i+1)+'</a></li>';");
		    sb.append("}");
		    sb.append("}");
		    sb.append("if(pagelong*n<data.obj.pageCount){");
		    sb.append("html+='<li class=\"paginItem more\"><a href=\"javascript:;\">...</a></li>';");
		    sb.append("html+='<li class=\"paginItem\"><a href=\"javascript:setPageNow('+data.obj.pageCount+');\">'+data.obj.pageCount+'</a></li>';");
		    sb.append("}");
		    sb.append("if(data.obj.pageNo==data.obj.pageCount){");//如果是最后一页的话，不给跳转
		    sb.append("html+='<li class=\"paginItem\"><a href=\"javascript:void(0);\"><span class=\"pagenxt\"></span></a></li></ul>';");
		    sb.append("}else{");
		    sb.append("html+='<li class=\"paginItem\"><a href=\"javascript:setPageNow('+(data.obj.pageNo+1)+');\"><span class=\"pagenxt\"></span></a></li></ul>';");
		    sb.append("}");
		    sb.append(" html +='</div>';");
     		sb.append("$('#"+this.name+"').append(html);");
		    /******分页部分 end*******/
		    }
		 sb.append("}"); 
       sb.append("  },"); 
         //调用出错执行的函数
       sb.append(" error: function(data){"); 
       sb.append("	 alert('网络异常');"); 
       sb.append(" } "); 
		sb.append("});");
		sb.append("}");
		
		sb.append("roadDataGrid();");//////////////////////////执行datagrid
		sb.append("$('."+this.style+" tbody tr:odd').addClass('odd');");
		sb.append("</script>");
		
		out.print(sb.toString());
		out.flush();
		
	}
	
	private   String  basePath(){
		String path =pageContext.getServletContext().getContextPath();
		String basePath = pageContext.getRequest().getScheme()+"://"+pageContext.getRequest().getServerName()+":"+pageContext.getRequest().getServerPort()+path+"/rs/";
		return basePath;
	}
	
}
