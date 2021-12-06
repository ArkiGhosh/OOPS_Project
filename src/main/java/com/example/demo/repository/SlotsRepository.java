package com.example.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

import com.example.demo.model.Slots;

public interface SlotsRepository extends CrudRepository<Slots, String> {

    @AllowFiltering
    Slots findBySlotnum(String slotnum);

    @AllowFiltering
    Iterable<Slots> findBySpace(String space);


}