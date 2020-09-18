package com.taskagile.web.apis;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.taskagile.config.SecurityConfiguration;
import com.taskagile.domain.application.UserService;
import com.taskagile.domain.model.user.UsernameExistsException;
import com.taskagile.domain.model.user.EmailAddressExistsException;
import com.taskagile.utils.JsonUtils;
import com.taskagile.web.payload.RegistrationPayload;


/*	Test에서는 기본적으로 Spring Security만 인스턴스화 한다. 따라서 모든 요청에 대해 권한을 요청한다.-> http status 403 발생
 *	따라서 /api/registration 에 대해 permitted 될 수 있게 security config를 이런식으로 넣어줘야한다.
 *	또한 @ContextConfiguration 사용 시 @WebMvcTest 내의 Controller.class가 작동하지 않는다. @ContextConfiguration에는 @Component나 @Configuration등이 사용된 클레스를 로드할 수 있으므로 여기 넎어준다!
 */
@ContextConfiguration(classes = { SecurityConfiguration.class,RegistrationApiController.class }) 
@RunWith(SpringRunner.class)
@WebMvcTest // 이거는 있어야한다. 있어야 Spring Mvc Component 사용가능하다.
@ActiveProfiles("test")
public class RegistrationApiControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private UserService serviceMock;
	
	@Test
	public void register_blankPayload_shouldFailAndReturn400() throws Exception {
		mvc.perform(post("/api/registrations"))
			.andExpect(status().is(400));
	}

	@Test
	public void register_existedUsername_shouldFailAndReturn400() throws Exception {
		RegistrationPayload payload = new RegistrationPayload();
		payload.setUsername("exists");
		payload.setEmailAddress("test@taskagile.com");
		payload.setPassword("MyPassword!");
		
		doThrow(UsernameExistsException.class)
			.when(serviceMock)
			.register(payload.toCommand());
		
		mvc.perform(post("/api/registrations")
				.contentType(MediaType.APPLICATION_JSON)
				.content(JsonUtils.toJson(payload)))
		.andExpect(status().is(400))
		.andExpect(jsonPath("$.message").value("Username already exists"));
	}
	
	@Test
	public void register_existedEmailAddress_shouldFailAndReturn400() throws Exception {
		RegistrationPayload payload = new RegistrationPayload();
		payload.setUsername("test");
		payload.setEmailAddress("exist@taskagile.com");
		payload.setPassword("MyPassword!");
		
		doThrow(EmailAddressExistsException.class)
			.when(serviceMock)
			.register(payload.toCommand());
		
		mvc.perform(post("/api/registrations")
				.contentType(MediaType.APPLICATION_JSON)
				.content(JsonUtils.toJson(payload)))
		.andExpect(status().is(400))
		.andExpect(jsonPath("$.message").value("Email address already exists"));
		// jsonPath -> response body로 access
		// $.message : 찾아봐야함. key값을 의미하는듯?
	}
	
	@Test
	public void register_validPayload_shouldSucceedAndReturn201() throws Exception {
	    RegistrationPayload payload = new RegistrationPayload();
	    payload.setUsername("sunny");
	    payload.setEmailAddress("sunny@taskagile.com");
	    payload.setPassword("MyPassword!");
	    
	    doNothing().when(serviceMock).register(payload.toCommand());
	    
	    mvc.perform(post("/api/registrations")
	    		.contentType(MediaType.APPLICATION_JSON)
	    		.content(JsonUtils.toJson(payload)))
	    .andExpect(status().isCreated());
	}

}
