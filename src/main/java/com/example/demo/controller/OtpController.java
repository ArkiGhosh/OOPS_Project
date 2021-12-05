package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Random;
import static org.springframework.data.cassandra.core.query.Criteria.where;
import static org.springframework.data.cassandra.core.query.Query.query;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
@RestController
@CrossOrigin
public class OtpController {

@Autowired
private CassandraOperations cassandraOperations;

@Autowired
 UserRepository userRepository;


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

 @GetMapping("setact/{mail}")
  public String setAct(@PathVariable String mail)
 {
    
    User us = cassandraOperations.selectOne(query(where("email").is(mail)).withAllowFiltering(), User.class);

    us.setActive(true);
    userRepository.save(us);

    return "{\"hehe\" : \"jeje\"}";
  

 }

}
