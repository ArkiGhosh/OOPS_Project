package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

//import com.example.demo.model.Feedback;
import com.example.demo.model.Worker;
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
    
    @CrossOrigin
    @PostMapping("/add_worker")   
    public String add_worker(@RequestBody Worker new_worker)
    {
        
       Worker worker = new_worker;    
        worker.setId(UUID.randomUUID().toString());
       workerRepository.save(worker);
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


/*
    @PostMapping("/feedback/{id}")
    public void feedback(@RequestBody Feedback feedback)
    {
        
    } 
    */
}