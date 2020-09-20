package com.taskagile.domain.model.user;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

// UserDetailsService의 구현체인 UserServiceImpl이 Load하게 될 UserDetails의 구현체
public class SimpleUser implements UserDetails, Serializable {

	private static final long serialVersionUID = -2563417160791098534L;

	private final long userId;
	private final String username;
	private final String password;
	
	public SimpleUser(User user) {
		this.userId = user.getId();
		this.username = user.getUsername();
		this.password = user.getPassword();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
	}
	
	public long getUserId() {
		return userId;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() { return true; }

	@Override
	public boolean isAccountNonLocked() { return false; }

	@Override
	public boolean isCredentialsNonExpired() { return false; }

	@Override
	public boolean isEnabled() { return false; }
	
	

}
