package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

import com.example.demo.model.Slots;

public interface SlotsRepository extends CrudRepository<Slots, String> {


}