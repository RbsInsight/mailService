package com.rbs.mailService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@SpringBootApplication
public class MailServiceApplication {
	@Value("${gmail.username}")
	private String username;

	@Value("${gmail.password}")
	private String password;

	public static void main(String[] args) {
		SpringApplication.run(MailServiceApplication.class, args);
	}

        @CrossOrigin(origins = "*", allowedHeaders = "*")
	@RequestMapping(value="/sendmail",method= RequestMethod.POST)
	public String sendMail(@RequestBody MailMessage mailmessage) throws AddressException, MessagingException, IOException {

		sendEmail(mailmessage) ;
		return "Mail sent successfully...!";
	}

	private void sendEmail(MailMessage mailmessage) throws AddressException, MessagingException, IOException {
		Properties props = new Properties();
		props.put("mail.smtp.auth","true");
		props.put("mail.smtp.starttls.enable","true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator(){
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(username, false));

		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailmessage.getMail_to_address()));
		msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(mailmessage.getMail_cc_address()));
		msg.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(mailmessage.getMail_bcc_address()));
		msg.setSubject(mailmessage.getMail_subject());
		msg.setContent(mailmessage.getMail_body(), "text/html");
		msg.setSentDate(new Date());



		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(mailmessage.getMail_body(), "text/html");

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);

		MimeBodyPart attachPart = new MimeBodyPart();
		attachPart.attachFile("C:\\image.jpeg");
		multipart.addBodyPart(attachPart);
		msg.setContent(multipart);


		Transport.send(msg);


	}

}
