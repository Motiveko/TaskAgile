package com.taskagile;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class) // 이거는 왜 붙이는지 잘 모르겠따.
@ActiveProfiles("test") // test/resources/application.properties를 읽을 수 있게한다.
public class TaskAgileApplicationTests {

	@Test
	public void contextLoads() {
		
	}

}
