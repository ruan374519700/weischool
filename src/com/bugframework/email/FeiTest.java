package com.bugframework.email;

public class FeiTest {
public static void main(String[] args) {
	   //这个类主要是设置邮件   
    MailSenderInfo mailInfo = new MailSenderInfo();    
    mailInfo.setMailServerHost("smtp.139.com");    
    mailInfo.setMailServerPort("25");    
    mailInfo.setValidate(true);    
    mailInfo.setUserName("xuzengfei@139.com");    
    mailInfo.setPassword("fei_huan");//您的邮箱密码    
    mailInfo.setFromAddress("xuzengfei@139.com");    
    mailInfo.setToAddress("798112095@qq.com");    
    mailInfo.setSubject("请到www.gdpuwsxh.com 发布网站");    
    mailInfo.setContent("请到www.gdpuwsxh.com 发布网站");    
       //这个类主要来发送邮件   
    SimpleMailSender sms = new SimpleMailSender();   
    //    sms.sendTextMail(mailInfo);//发送文体格式    
        sms.sendHtmlMail(mailInfo);//发送html格式   
}
}
