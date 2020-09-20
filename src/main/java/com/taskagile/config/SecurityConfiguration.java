package com.taskagile.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	// antMatchers(PUBLIC)으로 Spring Security가 처리하기 원하는 요청의 경로를 명시한다!!
	private static final String[] PUBLIC = 
			new String[] {"/error", "/login", "/logout", "/register", "/api/registrations"};

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// super.configure(http);
		http
			.authorizeRequests() // 모든 http 요청을 처리할것이다
				.antMatchers(PUBLIC).permitAll() // permitAll을 통해 Spring security는 해당 요청에 대해 모두에게 열어준다. 
				.anyRequest().authenticated() // 나머지는 인증된 사람에게만 요청한다
				.and() // 메소드 체인을 다시 HttpSecurity로 변경
			.formLogin()
				.loginPage("/login")
				.and()
			.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/login?logged-out")
				.and()
			.csrf().disable(); // csrf : cross-site-request-forgery
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// super.configure(web);
		web.ignoring().antMatchers("/static/**", "/js/**", "/css/**", "/images/**", "/favicon.ico");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
