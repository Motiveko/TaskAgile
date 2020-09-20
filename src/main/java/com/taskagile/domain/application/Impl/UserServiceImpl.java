package com.taskagile.domain.application.Impl;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.taskagile.domain.application.UserService;
import com.taskagile.domain.application.commands.RegistrationCommand;
import com.taskagile.domain.common.event.DomainEventPublisher;
import com.taskagile.domain.common.mail.MailManager;
import com.taskagile.domain.common.mail.MessageVariable;
import com.taskagile.domain.model.user.RegistrationException;
import com.taskagile.domain.model.user.RegistrationManagement;
import com.taskagile.domain.model.user.SimpleUser;
import com.taskagile.domain.model.user.User;
import com.taskagile.domain.model.user.UserRepository;
import com.taskagile.domain.model.user.events.UserRegisteredEvent;

@Service
@Transactional
 class UserServiceImpl implements UserService{

	private RegistrationManagement registrationManagement;
	private MailManager mailManager;
	private DomainEventPublisher domainEventPublisher;
	private UserRepository userRepository;
	
	public UserServiceImpl(RegistrationManagement registrationManagement, 
							MailManager mailManager,
							DomainEventPublisher domainEventPublisher,
							UserRepository userRepository) {
		this.registrationManagement = registrationManagement;
		this.mailManager = mailManager;
		this.domainEventPublisher = domainEventPublisher;
		this.userRepository = userRepository;
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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if(StringUtils.isEmpty(username)) {
			throw new UsernameNotFoundException("No user found");
		}
		User user;
		if(username.contains("@")) {
			user = userRepository.findByEmailAddress(username);
		} else {
			user = userRepository.findByUsername(username);
		}
		if( user == null) {
			throw new UsernameNotFoundException("No user found by `" + username + "`");
		} 
		return new SimpleUser(user);
	}

}
