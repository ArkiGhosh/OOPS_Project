package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.ReactiveCassandraOperations;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Bookings;
import com.example.demo.repository.BookingsRepository;

 

@RestController
@CrossOrigin
public class BookingsController
{
@Autowired
private CassandraOperations cassandraOperations;

 @Autowired
 BookingsRepository bookingsRepository;

 
 @GetMapping("/bookings")
 public List<Bookings> getBookings()
 {
  Iterable<Bookings> result = bookingsRepository.findAll();
  List<Bookings> bookingsList = new ArrayList<Bookings>();
  result.forEach(bookingsList::add);
  return bookingsList;
 }





 
 @CrossOrigin
 @GetMapping("/bookings/{id}")
 public Optional<Bookings> getEmployee(@PathVariable String id)
 {
  Optional<Bookings> emp = bookingsRepository.findById(id);
  return emp;
 }
 

 @CrossOrigin
 @GetMapping("/bookings/update/{id}/{user}")
 public Optional<Bookings> updateEmployee(@PathVariable String id, @PathVariable String user)
 {
  Optional<Bookings> optionalBoo = bookingsRepository.findById(id);
  if (optionalBoo.isPresent()) {
   Bookings boo = optionalBoo.get();

    List<String> li = boo.getUsers();

    li.add(user);

   boo.setUsers(li);

   bookingsRepository.save(boo);


  }
  return optionalBoo;
 }




 @GetMapping("/bookings/space/{space}/date/{date}")
public List<Bookings> getEmployee(@PathVariable String space, @PathVariable String date)
 {
 // List<Bookings> boo = reactiveCassandraOperations.query("SELECT * FROM bookings WHERE date=? AND space=?",String.class,date,space);
 List<Bookings> boo = bookingsRepository.findBySpaceAndDate(space, date);

  return boo;
 }








 @DeleteMapping(value = "/bookings/del/{id}", produces = "application/json; charset=utf-8")
 public String deleteBooking(@PathVariable String id) {
  Boolean result = bookingsRepository.existsById(id);
  bookingsRepository.deleteById(id);
  return "{ \"success\" : "+ (result ? "true" : "false") +" }";
 }
@CrossOrigin 
 @PostMapping("/booking")
 public Bookings addBookings(@RequestBody Bookings newBooking)
 {
  String id = UUID.randomUUID().toString();
  Bookings boo = new Bookings(id, newBooking.getSlotid(), newBooking.getSpace(), newBooking.getCost(),newBooking.getIntime(),newBooking.getOuttime(),newBooking.getDate(),newBooking.getUsers(),newBooking.getServices());




  bookingsRepository.save(boo);

  return boo;
 }





}