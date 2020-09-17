package com.taskagile.domain.common.security;

import org.springframework.stereotype.Component;

@Component
public class PasswordEncryptorDelegator implements PasswordEncryptor {

	@Override
	public String encrypt(String rawPassword) {
		// TODO : 암호화로직은 추후 Spring-Security의 PasswordEncoder로 위임될 예정이다.	
		return rawPassword;
	}

}
