package com.bugframework.email;

import com.bugframework.common.utility.DataUtils;
import com.bugframework.common.utility.PropertiesUtil;

public class MailUtil {
	public static PropertiesUtil proper = new PropertiesUtil("mailconfig.properties");
	/**
	 * 发送邮件
	 * @param to  发到谁那里，如果是多个人的话，逗号隔开
	 * @param subject 邮件摘要
	 * @param content 邮件内容
	 * @return
	 */
	public static boolean  sendEmail(String to,String subject,String content){
			MailSenderInfo mailInfo = new MailSenderInfo();    
		    mailInfo.setMailServerHost(proper.readProperty("ServerHost"));    
		    mailInfo.setMailServerPort(proper.readProperty("ServerPort"));    
		    mailInfo.setValidate(true);    
		    mailInfo.setUserName(proper.readProperty("UserName"));    
		    mailInfo.setPassword(proper.readProperty("Password"));//您的邮箱密码    
		    mailInfo.setFromAddress(proper.readProperty("FromAddress"));    
		    mailInfo.setToAddress(to);    
		    mailInfo.setSubject(subject);    
		    mailInfo.setContent(content);  
		    boolean issend =SimpleMailSender.sendHtmlMail(mailInfo);//发送html格式
		    return issend;
	}
	/**
	 * 启动线程
	 * @param to  发到谁那里，如果是多个人的话，逗号隔开
	 * @param subject 邮件摘要
	 * @param content 邮件内容
	 */
	public static  void doTask(String to,String subject,String content){
		if(DataUtils.isStrNotEmpty(to)){
			String[] toarray =to.split(",");
			for(int i=0;i<toarray.length;i++){
				String sendContent = content;
				MailThread m = new MailThread();
				m.setTo(toarray[i]);
				m.setSubject(subject);
				sendContent+=" <a href='http://www.gdpuwsxh.com/auth/login.do?login'>点击这里进入武协官网后台</a>";
				m.setContent(sendContent);
				Thread t = new Thread(m);
				t.start();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
