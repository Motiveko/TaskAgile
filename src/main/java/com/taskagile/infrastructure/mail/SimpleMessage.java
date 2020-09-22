package com.taskagile.infrastructure.mail;

import com.taskagile.domain.common.mail.Message;

// 이 패키지 구조의 이유를 잘 모르겟따. SimpleUser는 model에 있는데..DDD를 공부해야 알 수 있을듯
public class SimpleMessage implements Message{

	private final String to;
	private final String subject;
	private final String body;
	private final String from;
	
	public SimpleMessage(String to, String subject, String body, String from) {
		this.to = to;
		this.subject = subject;
		this.body = body;
		this.from = from;
	}
	
	@Override
	public String getTo() {
		return to;
	}

	@Override
	public String getSubject() {
		return subject;
	}

	@Override
	public String getBody() {
		return body;
	}

	@Override
	public String getFrom() {
		return from;
	}

	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	
	
}
