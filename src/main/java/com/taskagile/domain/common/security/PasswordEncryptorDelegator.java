package com.taskagile.domain.common.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncryptorDelegator implements PasswordEncryptor {
	
	// Spring security의 PasswordEncoder, 구현체중 하나인 BCryptPasswordEncoder를 빈등록해주고 DI
	private final PasswordEncoder passwordEncoder;
	
	public PasswordEncryptorDelegator(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	@Override
	public String encrypt(String rawPassword) {
		return passwordEncoder.encode(rawPassword);
	}

}
