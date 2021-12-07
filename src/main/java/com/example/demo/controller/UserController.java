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
    {    Iterable<User> result = userRepository.findAll();
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
    
    @DeleteMapping(value = "/user/{id}", produces = "application/json; charset=utf-8")
    public String deleteuser(@PathVariable String id) {
    Boolean result = userRepository.existsById(id);
    userRepository.deleteById(id);
    return "{ \"success\" : "+ (result ? "true" : "false") +" }";
    }
    
    @CrossOrigin 
    @PostMapping("/add_user")
    public User adduser(@RequestBody User newuser)
    {
        newuser.setId(UUID.randomUUID().toString());
        userRepository.save(newuser);
        return newuser;
    }
    
    @CrossOrigin
    @GetMapping("/add_money/{user_id}/{amount}")
    public void add_money(@PathVariable String user_id, @PathVariable float amount)
    {
        Optional<User> optionaluser = userRepository.findById(user_id);
        User user = optionaluser.get();
        user.setBalance(amount);
        userRepository.save(user);
    }
    
    @CrossOrigin
    @GetMapping("/check_frequent/{user_id}")
    public String check_frequent(@PathVariable String user_id)
    {
        String promo = "FREQ_";
        boolean flag=false;
        Optional<User> optionaluser = userRepository.findById(user_id);
        User user = optionaluser.get();
        
        List<String> l = user.getBookingids();
        if (l!=null && l.size()>0 && (l.size())%5==0)
        {
            flag=true;
            promo = promo + String.valueOf(new Random().nextInt(10000));
            sendMail.sendPromoCode(promo, user.getEmail());
        }          
        System.out.println("{ \"success\" :" +promo+ "}"); 
        return "{ \"success\" :" +"\""+promo+ "\"" +"}";    
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
    @PostMapping("/feedback/{id}")
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
        
        String comment = feedback.getComments();
        List<String> comments_List = worker.getComments();
    
        if (comment != null)
        {
            comments_List.add(comment);
            worker.setComments(comments_List);
        }
    }
    
    */
}