package com.quizwit;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.admin.AdminRegister;
import com.student.StudentRegister;

@WebServlet("/email-verification")
public class EmailVerification extends HttpServlet {
	
	private static final long serialVersionUID = 4652855784469428732L;

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String email = req.getParameter("email");
		String error = "";
		String success = "";
		String otpResponse = "";
		HttpSession session = req.getSession();
		String userType = (String) session.getAttribute("userType");
		if(email == "")
			error = "E-mail required";
		else if(!Utility.validateEmail(email))
			error = "Invalid E-mail";
		else {
			AdminRegister ar = new AdminRegister();
			StudentRegister sr = new StudentRegister();
			try {
				Boolean result;
				if(userType == "admin")
					result = ar.adminExists(email);
				else result = sr.studentExists(email);
				if(!result) {
					otpResponse = EmailVerification.sendOtp(email);
					session.setAttribute("verifiedEmail", false);
					session.setAttribute("verifiedEmailDesc", email);
				}
				else {
					error = "Account with this E-mail already exists";
				}
				if(otpResponse != "") {
					success = "OTP sent successfully";
					session.setAttribute("emailOtp", otpResponse);
				}

				
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		JSONObject json = new JSONObject();
		
		json.put("success", success);
		json.put("error", error);
		PrintWriter printWriter = res.getWriter();
		printWriter.println(json.toString());
	}
	
	public static String sendOtp(String to) {
		String host = "smtp.gmail.com";
		final String user = "ishwar2303@gmail.com";
		final String password = "Baisla1999@";
		String otpString = "";
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.starttls.enable", "true");
		
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);			
			}
		});
		
		try {
			MimeMessage message = new MimeMessage(session); 
			message.setFrom(new InternetAddress(user));
		    message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		    message.setSubject("QuizWit E-mail Verfication");
		    otpString = GenerateOtp.create(6);
		    String msgString = "Your OTP for email verification is " + otpString; 
		    message.setText(msgString);
		    // send the message
		    Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return otpString;
	}
}
