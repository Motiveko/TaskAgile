package com.taskagile.domain.model.user;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.taskagile.domain.common.model.AbstractBaseEntity;

import lombok.Getter;

// javax.persistence 사용
@Entity
@Table(name="user")
@Getter
public class User extends AbstractBaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -538781580460070724L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "username", nullable = false, length = 50, unique = true)
	private String username;
	
	@Column(name = "email_address", nullable = false, length = 100, unique = true)
	private String emailAddress;
	
	@Column(name = "password", nullable = false, length = 45)
	private String password;
	
	@Column(name = "first_name", nullable = false, length = 45)
	private String firstName;
	
	@Column(name = "last_name", nullable = false, length = 45)
	private String lastName;
	
	// Date안에서 세부 타입을 지정하는 annotation,
	// Date: 년-월-일, Time: 시:분:초, TIMESTAMP: date + time
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", nullable = false)
	private Date createdDate;
	
	public User(){
		
	}
	
	public static User create(String username, String emailAddress, String password) {
		User user = new User();
		user.username = username;
		user.emailAddress = emailAddress;
		user.password = password;
		user.firstName = "";
		user.lastName = "";
		user.createdDate = new Date();
		return user;
	}
	
	// equals와 hashcode를 override해서 username과 emailAddress 로 user객체의 동일성을 비교한다.
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (!(o instanceof User)) return false;
	    User user = (User) o;
	    return Objects.equals(username, user.username) &&
	      Objects.equals(emailAddress, user.emailAddress);
	}

	@Override
	public int hashcode() {
	    return Objects.hash(username, emailAddress);
	}

	@Override
	public String toString() {
	    return "User{" +
	    	      "id=" + id +
	    	      ", username='" + username + '\'' +
	    	      ", emailAddress='" + emailAddress + '\'' +
	    	      ", password=<Protected> " +
	    	      ", firstName='" + firstName + '\'' +
	    	      ", lastName='" + lastName + '\'' +
	    	      ", createdDate=" + createdDate +
	    	      '}';
	}


}
