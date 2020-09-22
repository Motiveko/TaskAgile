package com.taskagile.config;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix="app")
@Validated
@Getter
@Setter
public class ApplicationProperties {

	// app.mailFrom이라는 메타데이터가 생성되어 application.properties에 해당 내용에 노랑물결이 사라진다.
	@Email
	@NotBlank
	private String mailFrom; 
}
