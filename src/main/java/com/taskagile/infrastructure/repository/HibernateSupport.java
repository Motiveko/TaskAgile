package com.taskagile.infrastructure.repository;

import javax.persistence.EntityManager;

import org.hibernate.Session;

public abstract class HibernateSupport  {

	// 상속받는 repository에서 사용할거다	
	protected final EntityManager entityManager;
	
	public HibernateSupport(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	Session getSession() {
		return entityManager.unwrap(Session.class);
	}
	
}
