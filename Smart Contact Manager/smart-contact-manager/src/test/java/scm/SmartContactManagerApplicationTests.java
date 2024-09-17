package scm;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import scm.service.EmailService;

@SpringBootTest
class SmartContactManagerApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private EmailService service;
	
	@Test
	void testMail(){
		service.sendEmail("kaleaniket042@gmail.com", "this is Testing", "i am the Fucking Devil..");
	}
}
