package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//import com.example.demo.model.Feedback;
//import com.example.demo.model.SlotRequest;
import com.example.demo.model.Slots;
import com.example.demo.model.User;
//import com.example.demo.model.Worker;
//import com.example.demo.repository.SlotRepository;
import com.example.demo.repository.UserRepository;
//import com.example.demo.repository.WorkerRepository;

 

@RestController
@CrossOrigin
public class UserController
{
    @Autowired SendMail sendMail;
    @Autowired UserRepository userRepository;
//    @Autowired SlotRepository slotRepository;
 //   @Autowired WorkerRepository workerRepository;


    
    @GetMapping("/users")
    public List<User> getusers()
    {
    Iterable<User> result = userRepository.findAll();
    List<User> usersList = new ArrayList<User>();
    result.forEach(usersList::add);
    return usersList;
    }
    
    @GetMapping("/user/{id}")
    public Optional<User> getuser(@PathVariable String id)
    {
    Optional<User> user = userRepository.findById(id);
    return user;
    }
    
    @PutMapping("/user/{id}")
    public Optional<User> updateuser(@RequestBody User newuser, @PathVariable String id)
    {
    Optional<User> optionaluser = userRepository.findById(id);
    if (optionaluser.isPresent()) {
    User user = optionaluser.get();
    user.setFirstname(newuser.getFirstname());
    user.setLastname(newuser.getLastname());
    user.setUsername(newuser.getUsername());
    user.setPassword(newuser.getPassword());
    user.setAddress(newuser.getAddress());
    user.setEmail(newuser.getEmail());
    user.setNumber(newuser.getNumber());
    user.setCrn(newuser.getCrn());
    user.setActive(newuser.getActive());
    userRepository.save(user);
    }
    return optionaluser;
    }
    
   
    @CrossOrigin
    @DeleteMapping(value="/remove_user/{id}")
    public void removeUser(@PathVariable String id)
    {
        userRepository.deleteById(id);

    }



    @CrossOrigin 
    @PostMapping("/add_user")
    public User adduser(@RequestBody User newuser)
    {
  String id = UUID.randomUUID().toString();
    User user = new User(
        id, 
        newuser.getFirstname(), 
        newuser.getLastname(), 
        newuser.getUsername(),
        newuser.getPassword(),
        newuser.getAddress(),
        newuser.getEmail(),
        newuser.getNumber(),
        newuser.getCrn(),
        newuser.getBalance(),
        newuser.getActive(),
        newuser.getBookingids()
        );

        userRepository.save(user);

    return user;
    }

    @CrossOrigin
    @PostMapping("/add_money/{user_id}/{amount}")
    public void add_money(@PathVariable String user_id, @PathVariable float amount)
    {
        Optional<User> optionaluser = userRepository.findById(user_id);
        User user = optionaluser.get();
        user.setBalance(amount);
    }

    
    
    @CrossOrigin
    @GetMapping("/pay/{id}/{cost}")
    public String pay(@PathVariable String id, @PathVariable float  cost)
    {
        Optional<User> optionaluser = userRepository.findById(id);
        User user = optionaluser.get();        
        boolean flag=true;

        if (cost > user.getBalance())
        {
            flag=false;
        }            
        else
        {
            float balance = user.getBalance();
            balance-=cost;
            user.setBalance(balance);
            String result = "Payment sucessful. Rs."+cost+" deducted from your wallet. Your current balance is Rs."+balance+" .";
            sendMail.sendPaymentConfirmation(result,user.getEmail()); 
            userRepository.save(user);            
        }

           
        return "{ \"success\" : "+ (flag ? "true" : "false") +" }";
    }

    @CrossOrigin
 @GetMapping("/user/update/{user}/{booking}")
 public Optional<User> updateEmployee(@PathVariable String user, @PathVariable String booking)
 {
  Optional<User> optionalBoo = userRepository.findById(user);
  if (optionalBoo.isPresent()) {
   User woo = optionalBoo.get();

    

List<String> li = new ArrayList<String>();
    if(woo.getBookingids()!=null)
        li = woo.getBookingids();
    li.add(booking);

   woo.setBookingids(li);

   userRepository.save(woo);
  }
  return optionalBoo;
 } 




/*
  @CrossOrigin
    @PostMapping("/feedback/{worker_id}")
    public void get_feedback(@RequestBody Feedback feedback,@PathVariable String worker_id)
    {
        Optional<Worker> optionalWorker = workerRepository.findById(worker_id);
        Worker worker = optionalWorker.get();
        int rating = feedback.getRating(); // send -1 if no value is entered

        if (rating != -1)
        {
            worker.setNo_of_ratings(worker.getNo_of_ratings()+1);
            worker.setTotal_ratings(worker.getTotal_ratings()+rating);
            worker.setAvg_rating((float)(worker.getTotal_ratings())/worker.getNo_of_ratings());
        }
        
        String comment = feedback.getComment();

        
        if (comment!=null)
        {
            List<String> comment_List = worker.getComments();
            comment_List.add(comment);
            worker.setComments(comment_List);
        }
        
        workerRepository.save(worker);
    }

    */
}