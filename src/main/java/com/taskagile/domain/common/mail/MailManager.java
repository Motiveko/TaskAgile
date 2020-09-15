package com.taskagile.domain.common.mail;

public interface MailManager {
	  /**
	   * Send a message to a recipient
	   *
	   * @param emailAddress : 메일 수신자의 주소
	   * @param subject : 메일 제목
	   * @param template : 메일 템플릿 파일
	   * @param variables : 메세지 템플릿의 변수들
	   */
	// Parameter에 ...사용 javascript ...과 비슷하다.	
	void send(String emailAddress, String subject, String tmplate, MessageVariable... variables);
}
