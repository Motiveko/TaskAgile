package com.taskagile.web.apis.authenticate;

import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;

import org.apache.commons.io.output.WriterOutputStream;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class AuthenticationFilterTest {
	
	// @MockBean :SpringRunner 사용시 AppContext에 bean으로 등록된다.
	@MockBean
	private AuthenticationManager authenticationManagerMock;
	
	
	@Test(expected = InsufficientAuthenticationException.class)
	public void attemptAuthentication_emptyRequestBody_shouldFail() throws IOException {
		MockHttpServletRequest request = new MockHttpServletRequest("POST", "/api/authentications"); // url문제있어도 작동한다!?
		AuthenticationFilter filter = new AuthenticationFilter();
		// filter.setAuthenticationManager(authenticationManagerMock);
		filter.attemptAuthentication(request, new MockHttpServletResponse());
	}
	
	@Test(expected = InsufficientAuthenticationException.class)
	public void attemptAuthentication_invalidJsonStringRequestBody_shouldFail() throws IOException {
		MockHttpServletRequest request = new MockHttpServletRequest("POST", "/api/authentications");
		AuthenticationFilter filter = new AuthenticationFilter();
		request.setContent("username=testusername&password=TestPassword!".getBytes());
		filter.attemptAuthentication(request, new MockHttpServletResponse());
	}
	
	@Test
	public void attemptAuthentication_validJsonStringRequestBody_shouldSuccess()throws IOException {
		MockHttpServletRequest request = new MockHttpServletRequest("POST", "/api/authentications");
//		{"username":"testusername","password":"TestPassword!"}
		request.setContent("{\"username\":\"testusername\",\"password\":\"TestPassword!\"}".getBytes());
		AuthenticationFilter filter = new AuthenticationFilter();
		filter.setAuthenticationManager(authenticationManagerMock);
		filter.attemptAuthentication(request, new MockHttpServletResponse());
		UsernamePasswordAuthenticationToken token = 
				new UsernamePasswordAuthenticationToken("testusername", "TestPassword!");
		verify(authenticationManagerMock).authenticate(token);
		
	}

}
