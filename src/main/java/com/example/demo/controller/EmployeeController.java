package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

 

@RestController
@CrossOrigin
public class EmployeeController
{

 @Autowired
 EmployeeRepository employeeRepository;


 @GetMapping(value = "/healthcheck", produces = "application/json; charset=utf-8")
 public String getHealthCheck()
 {
  return "{ \"isWorking\" : true }";
 }
 
 
 @GetMapping("/employees")
 public List<Employee> getEmployees()
 {
  Iterable<Employee> result = employeeRepository.findAll();
  List<Employee> employeesList = new ArrayList<Employee>();
  result.forEach(employeesList::add);
  return employeesList;
 }
 
 @GetMapping("/employee/{id}")
 public Optional<Employee> getEmployee(@PathVariable String id)
 {
  Optional<Employee> emp = employeeRepository.findById(id);
  return emp;
 }
 
 @PutMapping("/employee/{id}")
 public Optional<Employee> updateEmployee(@RequestBody Employee newEmployee, @PathVariable String id)
 {
  Optional<Employee> optionalEmp = employeeRepository.findById(id);
  if (optionalEmp.isPresent()) {
   Employee emp = optionalEmp.get();
   emp.setFirstname(newEmployee.getFirstname());
   emp.setLastname(newEmployee.getLastname());
   emp.setUsername(newEmployee.getUsername());
   emp.setPassword(newEmployee.getPassword());
   emp.setAddress(newEmployee.getAddress());
   emp.setEmail(newEmployee.getEmail());
   emp.setNumber(newEmployee.getNumber());
   emp.setCrn(newEmployee.getCrn());
   emp.setActive(newEmployee.getActive());
   employeeRepository.save(emp);
  }
  return optionalEmp;
 }
 
 @DeleteMapping(value = "/employee/{id}", produces = "application/json; charset=utf-8")
 public String deleteEmployee(@PathVariable String id) {
  Boolean result = employeeRepository.existsById(id);
  employeeRepository.deleteById(id);
  return "{ \"success\" : "+ (result ? "true" : "false") +" }";
 }
@CrossOrigin 
 @PostMapping("/employee")
 public Employee addEmployee(@RequestBody Employee newEmployee)
 {
  String id = UUID.randomUUID().toString();
  Employee emp = new Employee(id, newEmployee.getFirstname(), newEmployee.getLastname(), newEmployee.getUsername(),newEmployee.getPassword(),newEmployee.getAddress(),newEmployee.getEmail(),newEmployee.getNumber(),newEmployee.getCrn(),newEmployee.getActive());




  employeeRepository.save(emp);

  return emp;
 }

}