package com.taskagile.infrastructure.mail;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.taskagile.domain.common.mail.Message;

public class AsyncMailerTest {

	private AsyncMailer instance;
	private JavaMailSender mailSenderMock;
	
	@Before
	public void setup() {
		mailSenderMock = mock(JavaMailSender.class);
		instance = new AsyncMailer(mailSenderMock);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void send_nullMessage_shouldFail() {
		instance.send(null);	
	}
	
	@Test
	public void send_validMessage_shouldSucceed() {
		String to = "test@taskagile.com";
		String subject = "TestSubject";
		String body = "MailMessageBody";
		String from = "noreply@taskagile.com";
		
		Message message = new SimpleMessage(to, subject, body, from);
		
		instance.send(message);
		
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(to);
		mailMessage.setSubject(subject);
		mailMessage.setText(body);
		mailMessage.setFrom(from);
		
		// 이렇게 할려면 SimpleMailMessage는 필드의 값들이 같으면 동일하다는 동일성을 보장해야 하는것이다!
		// 그래서 hashcode equals의 override가 필요한것이다!! 깨달음을 얻었따.
		verify(mailSenderMock).send(mailMessage);
	}
}
