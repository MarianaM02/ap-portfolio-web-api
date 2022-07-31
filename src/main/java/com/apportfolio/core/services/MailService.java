package com.apportfolio.core.services;

import java.nio.charset.StandardCharsets;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.apportfolio.core.models.Mail;

@Service
public class MailService {

	private final JavaMailSender mailSender;

	@Value("${spring.mail.username}")
	private String mailFrom;

	/*
	 * @Value("${app.velocity.templates.location}") private String basePackagePath;
	 * 
	 * @Value("${app.token.password.reset.duration}") private Long expiration;
	 */

	public MailService(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	/**
	 * Sends a simple mail as a MIME Multipart message
	 */
	public void send(Mail mail) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
				StandardCharsets.UTF_8.name());

		helper.setTo(mail.getTo());
		helper.setText(mail.getContent(), true);
		helper.setSubject(mail.getSubject());
		helper.setFrom(mail.getFrom());
		mailSender.send(message);
	}
}