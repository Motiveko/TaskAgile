package com.taskagile.domain.model.user;

import org.springframework.stereotype.Component;

import com.taskagile.domain.common.security.PasswordEncryptor;

@Component
public class RegistrationManagement {

	// 생성자를 통한 DI에 final을 붙이면 좋다는 솔트룩스 면접관님의 말에 따라 붙여본다.
	// final로 선언되면 무조건 initialize해줘야하고 수정이 불가능해진다.
	private final PasswordEncryptor passwordEncryptor;
	private final UserRepository repository;
	
	public RegistrationManagement(PasswordEncryptor passwordEncryptor, UserRepository repository) {
		this.passwordEncryptor = passwordEncryptor;
		this.repository = repository;
	}

	public User register(String username, String emailAddress, String password) throws RegistrationException {
		
		User existingUser = repository.findByUsername(username);
		if(existingUser != null) throw new UsernameExistsException();
		
		existingUser = repository.findByEmailAddress(emailAddress);
		if( existingUser != null) throw new EmailAddressExistsException();
		
		
		String encryptPassword = passwordEncryptor.encrypt(password);
		User newUser = User.create(username, emailAddress.toLowerCase(), encryptPassword);
		repository.save(newUser);
		
		return newUser;
	}

}
