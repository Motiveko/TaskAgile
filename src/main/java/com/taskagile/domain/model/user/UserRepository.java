package com.taskagile.domain.model.user;

// HibernateUserRepository 와 UserRepository가 패키지 분류가 다르게되었다.
// UserRepository가 바로 jpa방식으로 사용됬다면 domain쪽이 아닌 infrastructure로 가게되는것인가?
public interface UserRepository {

	User findByUsername(String username);

	User findByEmailAddress(String emailAddress);

	void save(User userToSave);
}
