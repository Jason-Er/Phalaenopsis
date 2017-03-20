package com.phalaenopsis.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.phalaenopsis.model.HttpResult;

@RestController
@RequestMapping("/action")
public class LoginController {
	
	public static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> actionLogin() {
		logger.info("Invoke action Login");
		
		HttpResult status = new HttpResult(0,"OK",null);
		return new ResponseEntity<HttpResult>(status, HttpStatus.OK);	
	}
	
}
