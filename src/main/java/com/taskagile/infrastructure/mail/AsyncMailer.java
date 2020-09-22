package com.taskagile.infrastructure.mail;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.taskagile.domain.common.mail.Mailer;
import com.taskagile.domain.common.mail.Message;

@Component
public class AsyncMailer implements Mailer{

	public final static Logger log = LoggerFactory.getLogger(AsyncMailer.class);
	
	private final JavaMailSender mailSender;
	
	public AsyncMailer(JavaMailSender mailSender) {
		System.out.println("안녕하세요~@@@");
		System.out.println(mailSender.getClass().getName());
		this.mailSender = mailSender;
	}
	
	@Async
	@Override
	public void send(Message message) {
		Assert.notNull(message, "Parameter `message` must not be null");
		
		try {
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			
			if(StringUtils.isNotBlank(message.getFrom())) {
				mailMessage.setFrom(message.getFrom());
			}
			if(StringUtils.isNotBlank(message.getSubject())) {
				mailMessage.setSubject(message.getSubject());
			}
			if(StringUtils.isNotEmpty(message.getBody())) {
				mailMessage.setText(message.getBody());
			}
			if( StringUtils.isNotBlank(message.getTo())) {
				mailMessage.setTo(message.getTo());
			}
			mailSender.send(mailMessage);
			
		} catch (Exception e) {
			log.error("Failed to send mail message", e);
		}
	}

}
