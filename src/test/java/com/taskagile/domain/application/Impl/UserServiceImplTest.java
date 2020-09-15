package com.taskagile.domain.application.Impl;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.taskagile.domain.application.commands.RegistrationCommand;
import com.taskagile.domain.common.event.DomainEventPublisher;
import com.taskagile.domain.common.mail.MailManager;
import com.taskagile.domain.model.user.EmailAddressExistsException;
import com.taskagile.domain.model.user.RegistrationException;
import com.taskagile.domain.model.user.RegistrationManagement;
import com.taskagile.domain.model.user.User;
import com.taskagile.domain.model.user.UsernameExistsException;

public class UserServiceImplTest {


	private RegistrationManagement registrationManagementMock;
	private MailManager mailManager;
	private DomainEventPublisher eventPublisherMock;
	private UserServiceImpl instance;
	
	@Before
	public void setUp() {
		// 의존성 있는 field에 @Mock붙이고 MockitoAnnotations.initMocks(this)랑 같다.	
		registrationManagementMock = mock(RegistrationManagement.class)	;
		mailManager = mock(MailManager.class);
		eventPublisherMock = mock(DomainEventPublisher.class);
		instance = new UserServiceImpl(registrationManagementMock,
				mailManager,eventPublisherMock);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void register_nullCommand_shouldFail() throws RegistrationException {
		instance.register(null);
	}
	
	@Test(expected = UsernameExistsException.class)
	public void register_existingUsername_shouldFail() throws RegistrationException {
		String username = "existing";
		String emailAddress = "sunny@taskagile.com";
		String password = "MyPassword";
		doThrow(UsernameExistsException.class).when(registrationManagementMock)
			.register(username, emailAddress, password);
		RegistrationCommand command = new RegistrationCommand(username, emailAddress, password);
		instance.register(command);
	}
	
	@Test(expected = EmailAddressExistsException.class)
	public void register_existingEmailAddress_shouldFail() throws RegistrationException {
		String username = "sunny";
		String emailAddress = "existing@taskagile.com";
		String password = "MyPassword";
		doThrow(EmailAddressExistsException.class).when(registrationManagementMock)
			.register(username, emailAddress, password);
		RegistrationCommand command = new RegistrationCommand(username, emailAddress, password);
		instance.register(command);		
	}
	
	@Test
	public void register_vaildCommand_shouldSucceed() throws RegistrationException {
		String username = "sunny";
		String emailAddress = "sunny@taskagile.com";
		String password = "MyPassword";
		User newUser = User.create(username, emailAddress, password);
		when(registrationManagementMock.register(username, emailAddress, password))
			.thenReturn(newUser);
	}

}
