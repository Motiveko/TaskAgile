package com.taskagile.utils;

import java.util.HashMap;

import org.springframework.util.Assert;

// ResponseBody에 key-value 로 data를 넣기 위해 HashMap을 상속하였다!!!!
// 이렇게하면 message, errorcode 외에 다른거를 여러개 넣어서 한꺼번에 responsebody에 담을 수 없지 않은가?
public class ApiResult extends HashMap<String, Object>{
	private static final long serialVersionUID = 7825998974010228696L;
	
	// 사용할 key값은 미리 생성?
	private static final String MESSAGE_KEY = "message";
	private static final String ERROR_CODE_KEY = "errorReferenceCode";
	
	public static ApiResult blank() {
		return new ApiResult();
	}

	public static ApiResult message(String message) {
		Assert.hasText(message, "Parameter `message` must not be blank");
		ApiResult apiResult = new ApiResult();
		// HashMap의 method를 그대로 사용 가능, key는 우리가 선언해놓은 상수값을 사용한다.	
		apiResult.put(MESSAGE_KEY, message); 
		return apiResult;
	}
	
	public static ApiResult error(String message, String errorReferenceCode) {
		Assert.hasText(message, "Parameter `message` must not be blank");
		Assert.hasText(errorReferenceCode, "Parameter `errorReferenceCode` must not be blank");
	
		ApiResult apiResult = new ApiResult();
		apiResult.put(MESSAGE_KEY, message);
		apiResult.put(ERROR_CODE_KEY, errorReferenceCode);
		return apiResult;
	}
	
	// Custom key,value
	public ApiResult add(String key, Object value) {
		Assert.hasText(key, "Parameter `key` must not be blank");
		Assert.notNull(value, "Parameter `value` must not be blank");
		
		// ??!?!
		this.put(key, value);
		return this;
	}
}
