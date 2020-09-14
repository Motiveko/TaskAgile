package com.taskagile.domain.application.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RegistrationCommand {

	// 굳에 RegistrationPayload와 분리된거는 유효성 검증 < - > 비즈니스 로직 분리하기 위함인가?
	// 여기에 tostring, hashcode, equals 는 비즈니스 로직인가?	
	private String username;
	private String emailAddress;
	private String password;
	
	@Override
	public boolean equals(Object o) {
		if( this == o) return true;
		if( o == null || getClass() != o.getClass()) return false;
		
		RegistrationCommand that = (RegistrationCommand) o;
		// 문법이 간결하다.
		if( username != null ? !username.equals(that.username) : that.username !=null) return false;
		if( emailAddress != null ? !emailAddress.equals(that.emailAddress) : that.username != null) return false;
		return password != null ? password.equals(that.password) : that.password == null;
	}
	@Override
	public int hashCode() {
		int result = username != null ? username.hashCode() : 0;
		result = 31 * result + (emailAddress != null ? emailAddress.hashCode() : 0);
		result = 31 * result + (password != null ? password.hashCode() : 0);
		return result;
	}
	@Override
	public String toString() {
		return "RegistrationCommand{" +
			"username='" + username + '\'' +
			", emailAddress='" + emailAddress + '\'' +
			", password='" + password + '\'' +
			'}';
	}
	

}
