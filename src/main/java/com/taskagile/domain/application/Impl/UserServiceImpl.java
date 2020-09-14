package com.taskagile.domain.application.Impl;

import org.springframework.stereotype.Service;

import com.taskagile.domain.application.UserService;
import com.taskagile.domain.application.commands.RegistrationCommand;
import com.taskagile.domain.model.user.RegistrationException;

@Service
public class UserServiceImpl implements UserService{

	@Override
	public void register(RegistrationCommand command) throws RegistrationException {
		// TODO 등록 비즈니스 로직 구현하귀
	}

}
