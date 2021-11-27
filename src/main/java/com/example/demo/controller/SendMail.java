package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendMail {

        @Autowired JavaMailSender sender;
        
        public void sendMail(String otp,String email)
        {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("testnumber1024@gmail.com");
            mailMessage.setTo(email);
            mailMessage.setSubject("OTP requested");
            
            String message = "Your OTP is "+otp+". This will expire in 5 mins. Please ignore if not requested.";
            mailMessage.setText(message);

            sender.send(mailMessage);
            System.out.println("Mail sent !!!!");

        }
}