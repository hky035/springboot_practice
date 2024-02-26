package com.heo.rest.webservice.restfulwebservice.helloworld;

import java.util.Locale;

import org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWordController {
	
	private MessageSource messageSource; // 국제화
	
	public HelloWordController(MessageSource messageSource) {
		super();
		this.messageSource = messageSource;
	}



	@GetMapping("/hello-world-i18n")
	public String helloWorldInternationalized() {
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage("good.morning.message", null, "Default Message", locale);
	}
}
