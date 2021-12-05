package com.example.demo.repository;

import com.example.demo.model.Spaces;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.repository.CrudRepository;


public interface SpacesRepository extends CrudRepository<Spaces,String> {
    
}

