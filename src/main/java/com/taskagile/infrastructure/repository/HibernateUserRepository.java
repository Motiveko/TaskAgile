package com.taskagile.infrastructure.repository;

import javax.persistence.EntityManager;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.taskagile.domain.model.user.User;
import com.taskagile.domain.model.user.UserRepository;

@Repository
public class HibernateUserRepository extends HibernateSupport implements UserRepository {
	
	public HibernateUserRepository(EntityManager entityManager) {
		// 상속받은 HibernateSupport 클래스에서 entityManager DI 이뤄진다. 처음보는 구조다! 신기방기
		super(entityManager);
	}

	// 노랑불뜬다. 의미는 약간 지는물인 class나 그것의 method를 쓸 때 붙이는듯?
	@SuppressWarnings("deprecation") 
	@Override
	public User findByUsername(String username) {
		// hibernte의 Query는 jpa Query와 다르게 Generic을 지원한다.
		Query<User> query = getSession().createQuery("from User where username = :username", User.class);
		query.setParameter("username", username);
		return query.uniqueResult();
	}
	
	@SuppressWarnings("deprecation") 
	@Override
	public User findByEmailAddress(String emailAddress) {
		Query<User> query = getSession().createQuery("from User where emailAddress = :emailAddress", User.class);
		query.setParameter("emailAddress", emailAddress);		
		return query.uniqueResult();
	}

	@Override
	public void save(User userToSave) {
		// userToSave를 EntityManager의 관리 하에 둔다. 그리고 Persistence Context에 instance를 추가한다.
		entityManager.persist(userToSave); 
		// flush() : Synchronize the persistence context to the underlying database.
		// flsuh를 하면 sql statement를 database에 전송한다고 한다. 아직 commit은 된게 아니라고한다.
		entityManager.flush();
		// 추가 : UserServiceImpl에 @Transactional에서 문제없으면 commit해줄거다!
	}

}
