package com.taskagile.web.apis.authenticate;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.taskagile.utils.JsonUtils;

import lombok.Getter;
import lombok.Setter;

// AbstractAuthenticationProcessingFilter을 상속하는 UsernamePasswordAuthenticationFilter를 커스터마이징 할 필터
// AuthenticationFilter가 모든 API요청을 처리할 것이므로 web.~ package에 놓는다
public class AuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	private static final Logger log = LoggerFactory.getLogger(AuthenticationFilter.class);
	
	public AuthenticationFilter() {
		// AntPathStyle :: https://stackoverflow.com/questions/2952196/learning-ant-path-style
		// /login이 아닌 우리가 사용하는 경로에 POST요청에 대한 필터설정
		super(new AntPathRequestMatcher("/api/authentications", "POST"));
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException {
		log.debug("Processing login request");

		String requestBody = IOUtils.toString(request.getReader());
		// request가 json형태로 들어왔다면 LoginRequest객체로 변환할 수 있을것이다.
		LoginRequest loginRequest = JsonUtils.toObject(requestBody, LoginRequest.class);
		
		if(loginRequest == null || loginRequest.isInvalid()) {
			throw new InsufficientAuthenticationException("Invalid authentication request");
		}
		
		// 인증 전 authentication, principal : username, credentials: password, authorities: '', authenticated: false
		// getAuthenticationManager에서 인증에 성공하면 token정보가 갱신되고 authenticated: true가 될 것이다!
		UsernamePasswordAuthenticationToken token = 
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
		return this.getAuthenticationManager().authenticate(token);
	}
	
	@Getter
	@Setter
	static class LoginRequest{
		
		private String username;
		private String password;
		
		public boolean isInvalid() {
			return StringUtils.isBlank(username) || StringUtils.isBlank(password);
		}
	}
}
