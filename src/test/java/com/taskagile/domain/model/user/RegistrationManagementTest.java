package com.taskagile.domain.model.user;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;
import static org.mockito.Mockito.inOrder;
import com.taskagile.domain.common.security.PasswordEncryptor;

public class RegistrationManagementTest {

	private PasswordEncryptor passwordEncryptorMock;
	private UserRepository repositoryMock;
	private RegistrationManagement instance;
	
	
	@Before
	public void setup() {
		passwordEncryptorMock = mock(PasswordEncryptor.class);
		repositoryMock = mock(UserRepository.class);
		instance = new RegistrationManagement(passwordEncryptorMock,repositoryMock);
	}
	
	// 이미 존재하는 username으로 등록 시도
	@Test(expected = UsernameExistsException.class)
	public void register_existedUsername_shouldFail() throws RegistrationException {
		
		String username = "existUsername";
		String emailAddress = "sunny@taskagile.com";
		String password = "MyPassword!";
		// username이 이미 존재할 경우 empty User를 되돌려준다.
		when(repositoryMock.findByUsername(username)).thenReturn(new User());
		
		instance.register(username, emailAddress, password);
	}
	
	// 이미 존재하는 email로 등록 시도
	@Test(expected = EmailAddressExistsException.class)
	public void register_existedEmailAddress_shouldFail() throws RegistrationException {
		
		String username = "sunny";
		String emailAddress = "existEmail@taskagile.com";
		String password = "MyPassword!";
		when(repositoryMock.findByEmailAddress(emailAddress)).thenReturn(new User());
		
		instance.register(username, emailAddress, password);
	}
	
	// 이메일 주소가 소문자로 저장되는지 확인
	@Test
	public void register_uppercaseEmailAddress_shouldSucceedAndBecomeLowercase() throws RegistrationException {
		String username = "sunny";
		String emailAddress = "Sunny@TaskAgile.com";
		String password = "MyPassword";
		
		instance.register(username, emailAddress, password);
		// repository가 email을 소문자로 변환한 UserEntity를 저장하는지 확인
		User userToSave = User.create(username, emailAddress.toLowerCase(), password);
		verify(repositoryMock).save(userToSave);	
	}
	
	// 유효한 데이터로 등록 시도시 password가 암호화되는지 확인
	@Test
	public void register_newUser_shouldSucceed() throws RegistrationException {
		String username = "sunny";
		String emailAddress = "existEmail@taskagile.com";
		String password = "MyPassword";		
		String encryptedPassword = "EncryptedPassword";
		
		User newUser = User.create(username, emailAddress.toLowerCase(), encryptedPassword);
		// username, emailAddress 중복 없음
		when(repositoryMock.findByUsername(username)).thenReturn(null);
		when(repositoryMock.findByEmailAddress(emailAddress)).thenReturn(null);
		doNothing().when(repositoryMock).save(newUser);;
		when(passwordEncryptorMock.encrypt(password)).thenReturn(encryptedPassword);
		
		User savedUser = instance.register(username, emailAddress, password);
		
		// Mockito의 InOrder API :: mock의 method 실행 순서에 대해 검사할 수 있는 API
		InOrder inOrder = inOrder(repositoryMock);
		inOrder.verify(repositoryMock).findByUsername(username);
		inOrder.verify(repositoryMock).findByEmailAddress(emailAddress);
		inOrder.verify(repositoryMock).save(newUser);
		
		// encrypt 하는지 여부 검사
		// encrypt 된 password로 저장했는지 검사
		verify(passwordEncryptorMock).encrypt(password);
		assertEquals("Saved user's password should be encrypted", encryptedPassword, savedUser.getPassword());
	}
}
