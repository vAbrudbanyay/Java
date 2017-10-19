package com.abrudbanyay.main;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class SpringBoot1Application {

	@ResponseBody
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	/*@RequestMapping("/")
	String entry(){
		return "index";
	}*/
	
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBoot1Application.class, args);
	}
}
