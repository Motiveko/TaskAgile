package com.taskagile.domain.application.Impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.taskagile.domain.application.UserService;
import com.taskagile.domain.application.commands.RegistrationCommand;
import com.taskagile.domain.common.event.DomainEventPublisher;
import com.taskagile.domain.common.mail.MailManager;
import com.taskagile.domain.common.mail.MessageVariable;
import com.taskagile.domain.model.user.RegistrationException;
import com.taskagile.domain.model.user.RegistrationManagement;
import com.taskagile.domain.model.user.User;
import com.taskagile.domain.model.user.events.UserRegisteredEvent;

@Service
@Transactional
 class UserServiceImpl implements UserService{

	private RegistrationManagement registrationManagement;
	private MailManager mailManager;
	private DomainEventPublisher domainEventPublisher;
	
	public UserServiceImpl(RegistrationManagement registrationManagement, MailManager mailManager,
			DomainEventPublisher domainEventPublisher) {
		this.registrationManagement = registrationManagement;
		this.mailManager = mailManager;
		this.domainEventPublisher = domainEventPublisher;
	}

	@Override
	public void register(RegistrationCommand command) throws RegistrationException {
		// TODO 등록 비즈니스 로직 
		Assert.notNull(command, "Parameter `command` must not be null");
		// 유저 등록
		User newUser = registrationManagement.register(
				command.getUsername(), command.getEmailAddress(), command.getPassword());
		// 메세지 전송
		sendWelcomeMessage(newUser);
		// UserRegisteredEventEvent publish
		domainEventPublisher.publish(new UserRegisteredEvent(newUser));
	}
	
	private void sendWelcomeMessage(User user) {
		mailManager.send(
				user.getEmailAddress(), 
				"Welcome to TaskAgile", 
				"welcom.ftl", 
				MessageVariable.from("user", user));
	}

}
