package com.taskagile.domain.common.mail;

public interface Message {

	// Recipient's mail address
	String getTo();
	
	// Subject of Message
	String getSubject();
	
	// Body of message
	String getBody();
	
	// Where this message is from
	String getFrom();
	
}
