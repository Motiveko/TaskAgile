package com.taskagile.domain.model.user.events;

import org.springframework.util.Assert;

import com.taskagile.domain.common.event.DomainEvent;
import com.taskagile.domain.model.user.User;

import lombok.Getter;

// equals, hashcode 구현하는 이유 -> 식별자로 사용하기 위함. 엔티티의 Id같은역할이라고 보면 된다. 
// 정확하게 언제 이것의 역할이 드러나는지 아직은 파악이 안되지만 곧 될듯하다. 
// 참고 : https://12bme.tistory.com/522?category=744863
@Getter
public class UserRegisteredEvent extends DomainEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2580061707540917880L;

	private User user;
	
	public UserRegisteredEvent(User user) {
		super(user);
		Assert.notNull(user, "Parameter `user` must not be null");
		this.user = user;
	}
	
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;

	    UserRegisteredEvent that = (UserRegisteredEvent) o;
	    return that.user.equals(this.user);
	  }

	  public int hashCode() {
	    return this.user.hashCode();
	  }

	  public String toString() {
	    return "UserRegisteredEvent{" +
	      "user='" + user + '\'' +
	      "timestamp='" + getTimestamp() + '\'' +
	      '}';
	  }
	
}
