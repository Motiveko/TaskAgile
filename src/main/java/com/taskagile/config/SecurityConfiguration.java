package com.taskagile.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.taskagile.web.apis.authenticate.AuthenticationFilter;
import com.taskagile.web.apis.authenticate.SimpleAuthenticationFailureHandler;
import com.taskagile.web.apis.authenticate.SimpleAuthenticationSuccessHandler;
import com.taskagile.web.apis.authenticate.SimpleLogoutSuccessHandler;

@EnableWebSecurity // @Configurtion 포함되어있다.
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
				.addFilterAfter(authenticationFilter(), UsernamePasswordAuthenticationFilter.class) // 필터를 커스텀필터로 대체
				.formLogin()
				.loginPage("/login")
			.and()
				.logout()
				.logoutUrl("/logout")
				.logoutSuccessHandler(logoutSuccessHandler())
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
	
	@Bean
	public AuthenticationFilter authenticationFilter() throws Exception {
		AuthenticationFilter authenticationFilter = new AuthenticationFilter();
		authenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
		authenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler());
		
		// WebSecurityConfigurerAdapter내 method, 
		// 정확히 어떤식으로 구현체 중 하나인 ProviderManager가 생성되고 DaoAuthenticationProvider까지 가는진 모르겠다
		authenticationFilter.setAuthenticationManager(authenticationManagerBean()); 
		return authenticationFilter;
	}
	
	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		return new SimpleAuthenticationSuccessHandler();
	}
	
	@Bean
	public AuthenticationFailureHandler authenticationFailureHandler() {
		return new SimpleAuthenticationFailureHandler();
	}
	
	@Bean 
	public LogoutSuccessHandler logoutSuccessHandler() {
		return new SimpleLogoutSuccessHandler();
	}
}
