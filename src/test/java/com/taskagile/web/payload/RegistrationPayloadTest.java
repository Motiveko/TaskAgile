package com.taskagile.web.payload;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;

public class RegistrationPayloadTest {

	// spring Initializer에서 만들어주는 project는 2.3.2인데 여기에는 javax.validation이 없다.
	// 버전마자 다른듯, 책과 같이 2.0.2 로 다운그레이드 하니 생겼다.
	private Validator validator;

	@Before
	public void setup() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	// 입력값 전부 blank
	@Test
	public void validate_blankPayload_shouldFail() {
		RegistrationPayload payload = new RegistrationPayload();
		Set<ConstraintViolation<RegistrationPayload>> violations = validator.validate(payload);
		assertEquals(3, violations.size());
		System.out.println(violations);
	}

	// 유효하지 않은 email
	@Test
	public void validate_payloadWithInvalidEmail_shouldFail() {
		RegistrationPayload payload = new RegistrationPayload();
		payload.setEmailAddress("BadEmailAddress");
		payload.setUsername("ValidUsername");
		payload.setPassword("ValidPassword");
		Set<ConstraintViolation<RegistrationPayload>> violations = validator.validate(payload);
		assertEquals(1, violations.size());

	}

	// 100자 이상의 email
	@Test
	public void validate_payloadWithEmailAddressLongerThan100_shouldFail() {

		// 101자 짜리 email 생성
		// The maximium allowed localPart is 64 characters
		// http://www.rfc-editor.org/errata_search.php?rfc=3696&eid=1690
		int maxLocalParthLength = 64;
		String localPart = RandomStringUtils.random(maxLocalParthLength, true, true);
		int usedLength = maxLocalParthLength + "@".length() + ".com".length();
		String domain = RandomStringUtils.random(101 - usedLength, true, true);

		RegistrationPayload payload = new RegistrationPayload();
		payload.setEmailAddress(localPart + "@" + domain + ".com");
		payload.setUsername("ValidUsername");
		payload.setPassword("ValidPassword");
		Set<ConstraintViolation<RegistrationPayload>> violations = validator.validate(payload);
		assertThat(violations.size(), is(1));
	}

	// 2자 이하의 unsername
	@Test
	public void validate_payloadWithUsernameShorterThan2_shouldFail() {
		RegistrationPayload payload = new RegistrationPayload();
		String usernameTooShort = RandomStringUtils.random(1);
		payload.setUsername(usernameTooShort);
		payload.setPassword("MyPassword");
		payload.setEmailAddress("sunny@taskagile.com");

		Set<ConstraintViolation<RegistrationPayload>> violations = validator.validate(payload);
		assertEquals(1, violations.size());
	}

	// 50자 이상의 username
	@Test
	public void validate_payloadWithUsernameLongerThan50_shouldFail() {
		RegistrationPayload payload = new RegistrationPayload();
		String usernameTooLong = RandomStringUtils.random(51);
		payload.setUsername(usernameTooLong);
		payload.setPassword("MyPassword");
		payload.setEmailAddress("sunny@taskagile.com");

		Set<ConstraintViolation<RegistrationPayload>> violations = validator.validate(payload);
		assertEquals(1, violations.size());
	}

	// 6자 이하의 비밀번호
	public void validate_payloadWithPasswordShorterThan6_shouldFail() {
	    RegistrationPayload payload = new RegistrationPayload();
	    String passwordTooShort = RandomStringUtils.random(5);
	    payload.setPassword(passwordTooShort);
	    payload.setUsername("MyUsername");
	    payload.setEmailAddress("sunny@taskagile.com");

	    Set<ConstraintViolation<RegistrationPayload>> violations = validator.validate(payload);
	    assertEquals(1, violations.size());
	}

	// 30자 이상의 비밀번호
	public void validate_payloadWithPasswordLongerThan30_shouldFail() {
	    RegistrationPayload payload = new RegistrationPayload();
	    String passwordTooLong = RandomStringUtils.random(31);
	    payload.setPassword(passwordTooLong);
	    payload.setUsername("MyUsername");
	    payload.setEmailAddress("sunny@taskagile.com");

	    Set<ConstraintViolation<RegistrationPayload>> violations = validator.validate(payload);
	    assertEquals(1, violations.size());
	}

}
