package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.Admin;
import com.example.demo.repository.AdminRepository;


@RestController
@CrossOrigin
public class AdminController {
    
@Autowired
AdminRepository adminRepository;    

 @GetMapping("/admins")
 public List<Admin> getEmployees()
 {
  Iterable<Admin> result = adminRepository.findAll();
  List<Admin> adminList = new ArrayList<Admin>();
  result.forEach(adminList::add);
  return adminList;
 }



}
