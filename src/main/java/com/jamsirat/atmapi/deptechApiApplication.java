package com.jamsirat.atmapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class deptechApiApplication {

//	@Autowired
//	private EmailSenderService emailSenderService;

	public static void main(String[] args) {
		SpringApplication.run(deptechApiApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void triggerMail() {
//		emailSenderService.sendSimpleEmail("thbdesabase@gmail.com",
//				"This is the email body..","this the email subject");

	}

}
