package com.taskagile.domain.common.event;

import org.springframework.context.ApplicationEvent;

public class DomainEvent extends ApplicationEvent {

	/*
	 *  DomainEvent --> org.springframework.context.ApplicationEvent --> 
	 *  	java.util.EventObject --> java.io.Serializable 로 구현이 이뤄져 마지막에도 serialVersionUID가 필요하다.
	 */
	private static final long serialVersionUID = -444783093811334147L;

	public DomainEvent(Object source) {
		super(source);
	}
	
	// 이 event발생한 시점(timestamp)
	public long occuredAt() {
		return getTimestamp();
	}
}
