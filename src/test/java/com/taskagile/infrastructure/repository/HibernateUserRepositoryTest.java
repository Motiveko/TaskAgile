package com.taskagile.infrastructure.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.hibernate.exception.ConstraintViolationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.taskagile.domain.model.user.User;
import com.taskagile.domain.model.user.UserRepository;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DataJpaTest
public class HibernateUserRepositoryTest {

	/*
	 * UserRepository <-- HibernateUserRepository 관계이기 때문에 Configuration이 필요하다. 만약
	 * UserRepository 자체를 Spring data jpa방식으로 활용했으면 @DataJpa에서 알아서 repository의 구현체를
	 * 생성한 다음 인스턴스화 해준단다.
	 */
	@TestConfiguration
	public static class UserRepositoryTestContextConfiguration {
		@Bean
		public UserRepository userRepository(EntityManager entityManager) {
			return new HibernateUserRepository(entityManager);
		}
	}

	@Autowired
	private UserRepository repository;

	// PersistenceException 은 persistence provider가 내는 HibernateException중 4개를 제외한
	// 모든걸 포함한다.
	@Test(expected = PersistenceException.class)
	public void save_nullUsernameUser_shoulFail() {
		User invalidUser = User.create(null, "sunny@taskagile.com", "MyPassword!");

		// repository는 mock이 아니기때문에 실제로 exceptiond을 던진다. @Column(nullable=false 이기
		// 때문이다!!
		repository.save(invalidUser);
	}

	@Test(expected = PersistenceException.class)
	public void save_nullEmailAddressUser_shouldFail() {
		User invalidUser = User.create("sunny", null, "MyPassword!");
		repository.save(invalidUser);
	}

	@Test(expected = PersistenceException.class)
	public void save_nullPasswordUser_shouldFail() {
		User inavlidUser = User.create("sunny", "sunny@taskagile.com", null);
		repository.save(inavlidUser);
	}

	@Test
	public void save_validUser_shouldSuccess() {
		String username = "sunny";
		String emailAddress = "sunny@taskAgile.com";
		User newUser = User.create(username, emailAddress, "MyPassword!");
		repository.save(newUser);
		assertNotNull("New user's id should be generated", newUser.getId()); // 잘못되면 AssertionError(message)
		assertNotNull("New user's created date should be generated", newUser.getCreatedDate());
		assertEquals(username, newUser.getUsername());
		assertEquals(emailAddress, newUser.getEmailAddress());
		assertEquals("", newUser.getFirstName());
		assertEquals("", newUser.getLastName());
	}

	// RegistrationManagement에서 한번 하고 넘겨주는 내용들인데 굳이 또 테스트를 하는 이유는..
	// RegistrationManagement에서는 mock으로 행동을 정해놓고 테스트한거고 여기서는 실제 save가 잘 동작하는지를
	// 테스트하는것이다!!
	@Test
	public void save_usernameAlreadyExist_shouldFail() {

		String username = "sunny";
		String emailAddress = "sunny@taskagile.com";
		User alreadyExist = User.create(username, emailAddress, "MyPassword!");
		repository.save(alreadyExist);

		try {
			User newUser = User.create(username, "new@taskagile", "MyPassword!");
			repository.save(newUser);
		} catch (Exception e) {
			// ConstraintViolationException -> JDBCException -> HibernateException ->
			// PersistenceExcption 순으로 상된다.
			// integrity(무결성) 위반시 뜨는 exception
			// instanceOf를 쓰지 않고 toString해서 assertEquals로 검사한다.
			assertEquals(ConstraintViolationException.class.toString(), e.getCause().getClass().toString());
		}
	}

	@Test
	public void save_emailAddressAlreadyExist_shouldFail() {

		String username = "sunny";
		String emailAddress = "sunny@taskagile.com";
		User alreadyExist = User.create(username, emailAddress, "MyPassword!");
		repository.save(alreadyExist);

		try {
			User newUser = User.create("new", emailAddress, "MyPassword!");
			repository.save(newUser);
		} catch (Exception e) {
			// ConstraintViolationException -> JDBCException -> HibernateException ->
			// PersistenceExcption 순으로 상된다.
			// integrity(무결성) 위반시 뜨는 exception
			// instanceOf를 쓰지 않고 toString해서 assertEquals로 검사한다.
			assertEquals(ConstraintViolationException.class.toString(), e.getCause().getClass().toString());
		}
	}

	// findByEmailAddress 잘 작동하는지 확인하는 테스트.
	// 이거를 사용하는 테스트는 RegistrationManagement에서 수행했으나 이 method자체는 repository test에서
	// 수행한다!
	@Test
	public void findByEmailAddress_notExist_shouldReturnEmptyResult() {
		String emailAddress = "sunny@taskagile.com";
		User user = repository.findByEmailAddress(emailAddress);
		assertNull("No user should be found", user);
	}

	// 저장 후에 잘 토해내는지 테스트한다.
	@Test
	public void findByEmailAddress_exist_shouldReturnResult() {
		String emailAddress = "sunny@taskagile.com";
		String username = "sunny";
		User newUser = User.create(username, emailAddress, "Mypassword");
		repository.save(newUser);
		User found = repository.findByEmailAddress(emailAddress);
		assertEquals("Username should math", username, found.getUsername());
	}

	// 이번엔 findByUsername 동작 테스트!
	@Test
	public void findByUsername_notExist_shouldReturnEmptyResult() {
		String username = "sunny";
		User user = repository.findByUsername(username);
		assertNull("No user should by found", user);
	}

	@Test
	public void findByUsername_exist_shouldReturnResult() {
		String username = "sunny";
		String emailAddress = "sunny@taskagile.com";
		User newUser = User.create(username, emailAddress, "MyPassword!");
		repository.save(newUser);
		User found = repository.findByUsername(username);
		assertEquals("Email address should match", emailAddress, found.getEmailAddress());
	}
}
