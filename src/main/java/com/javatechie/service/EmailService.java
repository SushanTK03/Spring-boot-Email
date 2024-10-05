package com.javatechie.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.javatechie.dto.EmailRequest;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender javaMailSender;  //class for convenient creation of JavaMail
	
	@Value("${spring.mail.username}")  //@value to load properties from yaml file
	private String sender;
	
	public String sendSimpleEmail(EmailRequest request) {
		SimpleMailMessage mailMessage=new SimpleMailMessage();
		mailMessage.setFrom(sender);
		mailMessage.setTo(request.getToEmail());
		mailMessage.setSubject(request.getSubject());
		mailMessage.setText(request.getMessageBody());
		javaMailSender.send(mailMessage);
		return "email successfully send to "+request.getToEmail();
	}
	
	
	public String sendEmailWithAttachment(EmailRequest request) throws MessagingException {
		MimeMessage mimeMessage=javaMailSender.createMimeMessage();
		
		MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,true);
		helper.setFrom(sender);
		helper.setTo(new String[] {"sushantkbemech@gmail.com","prashantkhamkar1294@gmail.com"});
		helper.setSubject(request.getSubject());
		helper.setText(request.getMessageBody());
		
		FileSystemResource file=new FileSystemResource(new File(request.getAttachment()));
		helper.addAttachment(file.getFilename(), file);
		
		javaMailSender.send(mimeMessage);
		return "Mail sent successfully with document "+file.getFilename();
		
	}

}
