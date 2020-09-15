package com.taskagile.domain.common.mail;

public class DefaultMailManager implements MailManager {

	@Override
	public void send(String emailAddress, String subject, String tmplate, MessageVariable... variables) {
		// TODO : 추후 메일 서비스 API 구현 후 호출예정
	}
}
