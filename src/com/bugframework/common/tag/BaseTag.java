package com.bugframework.common.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.bugframework.common.utility.DataUtils;

public class BaseTag extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String type = "default";// 加载类型
	protected String basePath;

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public int doStartTag() throws JspException {
		return EVAL_PAGE;
	}

	@Override
	public int doEndTag() throws JspException {
		try {
			JspWriter out = this.pageContext.getOut();
			basePath = this.pageContext.getServletContext().getContextPath()
					+ "/";
			StringBuffer sb = new StringBuffer();
			String types[] = type.split(",");
			sb.append(" <link href='" + basePath
					+ "rs/css/style.css' rel='stylesheet' type='text/css' />");
			if (DataUtils.isIn("jquery", types)) {// jquery控件
				sb.append("<script type='text/javascript' src='" + basePath
						+ "rs/js/jquery-1.8.3.js' ></script>");
			}
			if (DataUtils.isIn("ajaxform", types)) {// ajax表单提交控件
				sb.append("<script src='"
						+ basePath
						+ "rs/js/jquery.form.js' type='text/javascript'></script>");
			}
			if (DataUtils.isIn("imgupl", types)) {// ajax表单提交控件
				sb.append("<script src='"
						+ basePath
						+ "rs/js/swfupload/imgHandler.js' type='text/javascript'></script>");
			}
			if (DataUtils.isIn("fileupl", types)) {// ajax表单提交控件
				sb.append("<script src='"
						+ basePath
						+ "rs/js/swfupload/fileHandler.js' type='text/javascript'></script>");
			}
			if (DataUtils.isIn("swfuque", types)) {// ajax表单提交控件
				sb.append("<script src='"
						+ basePath
						+ "rs/js/swfupload/swfupload.queue.js' type='text/javascript'></script>");
			}
			if (DataUtils.isIn("swfu", types)) {// ajax表单提交控件
				sb.append("<script src='"
						+ basePath
						+ "rs/js/swfupload/swfupload.js' type='text/javascript'></script>");
			}
			if (DataUtils.isIn("editor", types)) {// ajax表单提交控件
				sb.append("<script src='"
						+ basePath
						+ "rs/js/ueditor/ueditor.config.js' type='text/javascript'></script>");
				sb.append("<script src='"
						+ basePath
						+ "rs/js/ueditor/ueditor.all.js' type='text/javascript'></script>");
				sb.append("<script src='"
						+ basePath
						+ "rs/js/ueditor/ueditor.parse.js' type='text/javascript'></script>");
				sb.append("<script src='"
						+ basePath
						+ "rs/js/ueditor/lang/zh-cn/zh-cn.js' type='text/javascript'></script>");
			}
			if (DataUtils.isIn("layer", types)) {// 弹出层控件
				sb.append("<script src='"
						+ basePath
						+ "rs/js/layer/layer.js' type='text/javascript'></script>");
			}
			if (DataUtils.isIn("tools", types)) {
				sb.append("<script src='" + basePath
						+ "rs/js/tools.js' type='text/javascript'></script>");
			}
			if (DataUtils.isIn("wdate", types)) {
				sb.append("<script src='"
						+ basePath
						+ "rs/js/My97DatePicker/WdatePicker.js' type='text/javascript'></script>");
			}

			if (DataUtils.isIn("valiform", types)) {
				sb.append("<script src='"
						+ basePath
						+ "rs/js/valiform/js/Validform_v5.3.2_min.js' type='text/javascript'></script>");
				sb.append("<link href='"
						+ basePath
						+ "rs/js/valiform/css/valiform.css' rel='stylesheet' type='text/css' />");
			}
			if (DataUtils.isIn("select", types)) {
				sb.append("<script src='"
						+ basePath
						+ "rs/js/select-ui.min.js' type='text/javascript'></script>");
				sb.append("<link href='"
						+ basePath
						+ "rs/css/select.css' rel='stylesheet' type='text/css' />");
			}

			out.print(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}
}
