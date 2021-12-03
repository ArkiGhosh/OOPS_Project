package com.example.demo.repository;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

import com.example.demo.model.Bookings;

public interface BookingsRepository extends CrudRepository<Bookings, String> {
    
    @AllowFiltering
    List<Bookings> findBySpaceAndDate(String space, String date);

}