package com.secbro2.mq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author sec
 * @version 1.0
 * @date 2020/8/17
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailTests {

	@Resource
	private JavaMailSender javaMailSender;

	@Test
	public void sendMail() {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom("123@qq.com");
		msg.setTo("123@qq.com");
		msg.setSubject("程序新视界");
		msg.setText("技术分享");
		// 抄送邮箱
		msg.setCc("abc@126.com","def@126.com");

		javaMailSender.send(msg);
	}

	@Test
	public void sendHtmlMail() {
		String content="<html>\n" +
				"<body>\n" +
				"    <h3>hello world ! 这是一封html邮件!</h3>\n" +
				"</body>\n" +
				"</html>";

		MimeMessage message = javaMailSender.createMimeMessage();
		try {
			// 第二个参数true表示需要创建一个multipart message
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom("123@qq.com");
			helper.setTo("123@qq.com");
			helper.setSubject("程序新视界");
			helper.setText(content, true);

			javaMailSender.send(message);
		} catch (MessagingException e){
			System.out.println("发送邮件异常");
		}
	}
}