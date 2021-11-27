package com.example.demo.worker;

import java.util.Optional;
import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WorkerController {

    @Autowired Worker worker;
    @Autowired WorkerRepository workerRepository;

    @PostMapping(value="/add worker")   
    public void add_worker(@RequestParam JSONObject jsonObject) throws JSONException
    {
       worker = worker.set_worker_details(jsonObject);
       workerRepository.save(worker);
    }

    @GetMapping(value="/get worker/{id}")
    public Worker getWorker(@PathVariable UUID id)
    {
        Optional <Worker> obj = workerRepository.findById(id);

        if (obj!=null)
            worker = obj.get();

        return worker;
    }

    @DeleteMapping(value="/remove worker/{id}")
    public void removeWorker(@PathVariable UUID id)
    {
        workerRepository.deleteById(id);
    }
}
