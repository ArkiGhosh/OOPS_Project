package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.demo.model.Slots;
import com.example.demo.model.Spaces;
import com.example.demo.repository.SlotsRepository;
import com.example.demo.repository.SpacesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class SpacesController {
    
    @Autowired SpacesRepository spacesRepository;
    @Autowired
    SlotsRepository slotsRepository;
    
    @CrossOrigin
    @GetMapping("/spaces")
    public List<Spaces> getSpaces()
    {
        Iterable<Spaces> result = spacesRepository.findAll();
        List<Spaces> employeesList = new ArrayList<Spaces>();
        result.forEach(employeesList::add);
        return employeesList;
    }

    @CrossOrigin
    @PostMapping("/add_space")
    public void add_spaces(@RequestBody Spaces space)
    {
        spacesRepository.save(space);
    }

    @CrossOrigin
    @DeleteMapping("/delete_space/{id}")
    public void delete_spaces(@PathVariable String id)
    {

        Optional<Spaces> spc = spacesRepository.findById(id);
        Spaces spccc = spc.get();
        String space = spccc.getSpace();
        spacesRepository.deleteById(id);

        Iterable<Slots> result = slotsRepository.findBySpace(space);
        List<Slots> employeesList = new ArrayList<Slots>();
        result.forEach(employeesList::add);
        List<String> ids = new ArrayList<String>();

        for(int i= 0;i<employeesList.size();i++){

            ids.add(employeesList.get(i).getId());

        }

        for(int i= 0;i<ids.size();i++){
            slotsRepository.deleteById(ids.get(i));
        }

    }

    
}