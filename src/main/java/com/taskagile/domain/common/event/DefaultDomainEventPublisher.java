package com.taskagile.domain.common.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class DefaultDomainEventPublisher implements DomainEventPublisher {

	private ApplicationEventPublisher actualPublisher;
	
	public DefaultDomainEventPublisher(ApplicationEventPublisher actualPublisher) {
		this.actualPublisher = actualPublisher;
	}
	// 퍼블리셔가 event를 publish하면 handler(subscriber)가 처리하게된다.
	@Override
	public void publish(DomainEvent event) {
		actualPublisher.publishEvent(event);
	}
}
