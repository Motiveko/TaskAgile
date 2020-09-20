package com.taskagile.domain.application.Impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.taskagile.domain.application.commands.RegistrationCommand;
import com.taskagile.domain.common.event.DomainEventPublisher;
import com.taskagile.domain.common.mail.MailManager;
import com.taskagile.domain.model.user.EmailAddressExistsException;
import com.taskagile.domain.model.user.RegistrationException;
import com.taskagile.domain.model.user.RegistrationManagement;
import com.taskagile.domain.model.user.User;
import com.taskagile.domain.model.user.UserRepository;
import com.taskagile.domain.model.user.UsernameExistsException;

public class UserServiceImplTest {


	private RegistrationManagement registrationManagementMock;
	private MailManager mailManager;
	private DomainEventPublisher eventPublisherMock;
	private UserServiceImpl instance;
	private UserRepository userRepositoryMock;
	
	@Before
	public void setUp() {
		// 의존성 있는 field에 @Mock붙이고 MockitoAnnotations.initMocks(this)랑 같다.	
		registrationManagementMock = mock(RegistrationManagement.class)	;
		mailManager = mock(MailManager.class);
		eventPublisherMock = mock(DomainEventPublisher.class);
		userRepositoryMock = mock(UserRepository.class);
		instance = new UserServiceImpl(registrationManagementMock,
				mailManager,eventPublisherMock, userRepositoryMock);
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
	
	// 인증 관련 추가된 loadUserByUsername() Test
	@Test(expected = UsernameNotFoundException.class)
	public void loadUserByUsername_emptyUsername_shouldFail() {
		
		instance.loadUserByUsername("");
		verify(userRepositoryMock, never()).findByUsername("");
		verify(userRepositoryMock, never()).findByEmailAddress("");
	}
	
	@Test(expected = UsernameNotFoundException.class)
	public void loadUserByUsername_notExistUsername_shouldFail() {
		String notExistUsername = "NotExistUsername";
		when(userRepositoryMock.findByUsername(notExistUsername)).thenReturn(null);
		instance.loadUserByUsername(notExistUsername);
		
		verify(userRepositoryMock).findByUsername(notExistUsername);
		verify(userRepositoryMock, never()).findByEmailAddress(notExistUsername);
	}
	
	@Test
	public void loadUserByUsername_existUsername_shouldSucceed() throws IllegalAccessException {
		String existUsername = "ExistUsername";
		User foundUser = User.create(existUsername, "user@taskagile.com", "EncryptedPassword!");
		
		// User에는 setter가 없다. SimpleUser는 User에서 id를 뽑아와야하는데 foundUser는 id없으므로 설정해준다.
		// 이외의 방법으로는 MockUser를 만드는 방법이 있따.
		// User mockUser = Mockito.mock(User.class)
		// when(mockUser.getId()).thenReturn(1L); ... 이런식으로 만들 수 있다.
		FieldUtils.writeField(foundUser, "id", 1L, true);
		when(userRepositoryMock.findByUsername(existUsername)).thenReturn(foundUser);
		
		
		UserDetails userDetails = null;
		userDetails = instance.loadUserByUsername(existUsername);
		
		verify(userRepositoryMock).findByUsername(existUsername);
		verify(userRepositoryMock,never()).findByEmailAddress(existUsername);
		assertEquals(existUsername, userDetails.getUsername());
		
	}
}
