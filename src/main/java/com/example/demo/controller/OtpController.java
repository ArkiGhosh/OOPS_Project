package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Random;
@RestController
@CrossOrigin
public class OtpController {

@Autowired SendMail sm;
@GetMapping("/otp/{mail}")
 public String getOtp(@PathVariable String mail)
 {
    

  String otp = String.valueOf(new Random().nextInt(10000));

  for(int i = otp.length()-4;i>0;i--){
      otp += '0';
  }


    System.out.println("----------------------------OTP "+otp+" ---------------------");

  sm.sendMail(otp,mail);
return "{ \"otp\" : "+ otp+" }";


 }
}
