package com.yash.email_sending;

import javax.mail.PasswordAuthentication;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Preparing message for sending...." );
        String message = "Hello friend this mail is testing do not worry";
        String subject = "Yash tesing mail API"; 
        String to = "sp3336705@gmail.com";
        String from ="yashsharma09140@gmail.com";
        sendMail(message, subject, to, from);
    }
    // this is responsible for send email...
	private static void sendMail(String message, String subject, String to, String from) {
		//variable for gmail
		String host = "smtp.gmail.com";
		
		//get the system properties
		Properties properties = System.getProperties();
		System.out.println(properties);
		//Setting important info to properties object
		//set host
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", 465);
		properties.put("mail.smtp.ssl.enable", true);
		properties.put("mail.smtp.auth", true);
		
		
		//Step 1: to get session object
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("xyz@gmail.com", "xyz");
			 }
		});
		if(session == null) {
			System.out.println("Session is null");
		}
		session.setDebug(true);
		//step 2: compose the message
		MimeMessage mimeMessage = new MimeMessage(session); 
		
		try {
			//set from
			mimeMessage.setFrom(from);
			
			//adding recipient to message 
			mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
			// adding subject to message 
			mimeMessage.setSubject(subject);
			
			//adding text to message
			mimeMessage.setText(message);
			
			//step 3: send the message using transport class
			Transport.send(mimeMessage);
			
			System.out.println("Sent Successfully");
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
