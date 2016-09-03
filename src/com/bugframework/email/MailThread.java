package com.bugframework.email;

/**
 * 启动email线程
 * @author Administrator
 *
 */
public class MailThread implements Runnable{
	private String to;
	private String subject;
	private String content;
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}


	@Override
	public void run() {
		MailUtil.sendEmail(to, subject, content);
	}

}
