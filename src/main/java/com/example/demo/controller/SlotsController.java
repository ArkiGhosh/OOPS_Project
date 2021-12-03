package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.Slots;
import com.example.demo.repository.SlotsRepository;
import java.util.UUID;

 

@RestController
@CrossOrigin
public class SlotsController
{

 @Autowired
 SlotsRepository slotsRepository;


@Autowired
private CassandraOperations cassandraOperations;


 
 @GetMapping("/slots")
 public List<Slots> getEmployees()
 {
  Iterable<Slots> result = slotsRepository.findAll();
  List<Slots> employeesList = new ArrayList<Slots>();
  result.forEach(employeesList::add);
  return employeesList;
 }

 /*
 @GetMapping("/employee/{id}")
 public Optional<Employee> getEmployee(@PathVariable String id)
 {
  Optional<Employee> emp = employeeRepository.findById(id);
  return emp;
 }
 
 @CrossOrigin
 @PostMapping("/slots")
 public Slots getSearch(@RequestBody Slots slot){



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
 */
@CrossOrigin 
 @PostMapping("/slots")
 public Slots addSlots(@RequestBody Slots newSlot)
 {
  //String id = String.valueOf(new Random().nextInt());
  String id = UUID.randomUUID().toString();
  Slots emp = new Slots(id, newSlot.getSlotnum(), newSlot.getSpace(),newSlot.getWorkerids());




  slotsRepository.save(emp);

  return emp;
 }

}
