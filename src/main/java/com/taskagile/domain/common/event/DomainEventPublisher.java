package com.taskagile.domain.common.event;

public interface DomainEventPublisher {
	// publish domain event
	void publish(DomainEvent event);
}
