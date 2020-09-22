package com.taskagile.domain.common.mail;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

import freemarker.template.Configuration;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class DefaultMailManagerTest {

	@TestConfiguration
	static class DefaultMessageCreatorConfiguration{
		@Bean
		public FreeMarkerConfigurationFactoryBean getFreemarkerConfiguration() {
			FreeMarkerConfigurationFactoryBean factoryBean = new FreeMarkerConfigurationFactoryBean();
			factoryBean.setTemplateLoaderPath("/mail-templates/");
			return factoryBean;
		}
	}
	
	@Autowired
	private Configuration configuration;
	private Mailer mailerMock;
	private DefaultMailManager instance;
	
	@Before
	public void setUp() {
		mailerMock = mock(Mailer.class);
		instance = new DefaultMailManager("noreply@taskagile.com", mailerMock, configuration);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void send_nullEmailAddress_shouldFail() {
		instance.send(null, "TestSubject", "test.ftl");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void send_emptyEmailAddress_shouldFail() {
		instance.send("", "TestSubject", "test.ftl");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void send_nullSubject_shouldFail() {
		instance.send("test@taskagile.com", null, "test.ftl");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void send_emptySubject_shouldFail() {
		instance.send("test@taskagile.com", "", "test.ftl");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void send_nullTemplateName_shouldFail() {
		instance.send("test@taskagile", "TestSubject", null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void send_emptyTemplateName_shouldFail() {
		instance.send("test@tas@agile", "TestSubject", "");
	}
	
	@Test
	public void send_validParameters_shouldSucceed() {
		// TODO : send validParam test코드 작성
		String to = "user@example.com";
		String subject = "Test subject";
		String templateName = "test.ftl";
		
		instance.send(to, subject, templateName, MessageVariable.from("name", "test"));
		
		// 여기서부터는 알아봐야 한다. ArgumentCaptor란? -- 일단 mockito네
		ArgumentCaptor<Message> messageArgumentCaptor = ArgumentCaptor.forClass(Message.class);
		// 이런식으로 method실행되는지 확인하면서 실제 실행 할 때 쓴 arguments를 쌔벼온다(왜냐면 parameter도 Message이기때문이다)
		verify(mailerMock).send(messageArgumentCaptor.capture()); 
		
		Message messageSent = messageArgumentCaptor.getValue();

		assertEquals(to, messageSent.getTo());
		assertEquals(subject, messageSent.getSubject());
		assertEquals("noreply@taskagile.com", messageSent.getFrom());
		assertEquals("Hello, test\n", messageSent.getBody());
	}
}
