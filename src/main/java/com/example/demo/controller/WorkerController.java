package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.demo.model.Feedback;
import com.example.demo.model.Slots;
//import com.example.demo.model.Feedback;
import com.example.demo.model.Worker;
import com.example.demo.repository.SlotsRepository;
import com.example.demo.repository.WorkerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


    @CrossOrigin
@RestController
@RequestMapping()
public class WorkerController {

    
    @Autowired WorkerRepository workerRepository;
    @Autowired SlotsRepository slotsRepository;
    
    @CrossOrigin
    @PostMapping("/add_worker")   
    public String add_worker(@RequestBody Worker new_worker)
    {
        
       Worker worker = new_worker;    
        worker.setId(UUID.randomUUID().toString());
       workerRepository.save(worker);

        Slots s = slotsRepository.findBySlotnum(worker.getSlot());
        s.setWorkerid(worker.getId());
        slotsRepository.save(s);

       return "added";
    }

    @CrossOrigin
    @GetMapping(value="/get_worker/{id}")
    public Worker getWorker(@PathVariable String id)
    {
        Worker worker = new Worker();
        Optional <Worker> obj = workerRepository.findById(id);

        if (obj!=null)
            worker = obj.get();

        return worker;
    }


    @CrossOrigin
    @GetMapping(value="/get_worker_by_slot/{slot}")
    public Worker getWorkerBySlot(@PathVariable String slot)
    {
        Worker worker = new Worker();
        Optional <Worker> obj = workerRepository.findBySlot(slot);

        if (obj!=null)
            worker = obj.get();

        return worker;
    }

    @PostMapping("/test")
    public String test()
    {
        return "test";
    }

    @CrossOrigin
    @DeleteMapping(value="/remove_worker/{id}")
    public void removeWorker(@PathVariable String id)
    {
        workerRepository.deleteById(id);

    }

    @CrossOrigin
 @GetMapping("/worker/update/{slot}/{booking}")
 public Optional<Worker> updateEmployee(@PathVariable String slot, @PathVariable String booking)
 {
  Optional<Worker> optionalBoo = workerRepository.findBySlot(slot);
  if (optionalBoo.isPresent()) {
   Worker woo = optionalBoo.get();
    List<String> li = new ArrayList<String>();
    if(woo.getBookingids()!=null)
        li = woo.getBookingids();
    li.add(booking);

   woo.setBookingids(li);

   workerRepository.save(woo);


  }
  return optionalBoo;
 } 


    @CrossOrigin
 @GetMapping("/workers")
    public List<Worker> getworkers()
    {
    Iterable<Worker> result = workerRepository.findAll();
    List<Worker> usersList = new ArrayList<Worker>();
    result.forEach(usersList::add);
    return usersList;
    }

     @CrossOrigin
    @PostMapping("/feedback/{slot}")
    public String get_feedback(@RequestBody Feedback feedback,@PathVariable String slot)
    {
        Optional<Worker> optionalWorker = workerRepository.findBySlot(slot);
        Worker worker = optionalWorker.get();
        int rating = feedback.getRating(); // send -1 if no value is entered

        if (rating != -1)
        {
            worker.setNo_of_ratings(worker.getNo_of_ratings()+1);
            worker.setTotal_ratings(worker.getTotal_ratings()+rating);
            worker.setAvg_rating((float)(worker.getTotal_ratings())/worker.getNo_of_ratings());
        }
        
        String comment = feedback.getComment();


        List<String> comment_List = new ArrayList<String>();

        
        if (comment!=null)
        {
            if(worker.getComments()!=null)
                comment_List = worker.getComments();
            comment_List.add(comment);
            worker.setComments(comment_List);
        
        }
        workerRepository.save(worker);

                return "{ \"rating\":"+worker.getAvg_rating()+"}";
    }

/*
    @PostMapping("/feedback/{id}")
    public void feedback(@RequestBody Feedback feedback)
    {
        
    } 
    */
}