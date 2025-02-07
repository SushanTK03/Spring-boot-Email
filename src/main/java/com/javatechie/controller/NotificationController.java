package com.javatechie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javatechie.dto.EmailRequest;
import com.javatechie.service.EmailService;

import jakarta.mail.MessagingException;

@RestController
public class NotificationController {
	
	@Autowired
	private EmailService service;
  @PostMapping("/sendEmail")
	public String sendSimpleEmail(@RequestBody EmailRequest request) {
		return service.sendSimpleEmail(request);
	}
  
  @PostMapping("/sendAttachment")
 	public String sendSimpleEmailWithAttachment(@RequestBody EmailRequest request) throws MessagingException {
 		return service.sendEmailWithAttachment(request);
 	}
 }

