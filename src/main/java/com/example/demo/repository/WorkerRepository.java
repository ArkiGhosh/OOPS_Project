package com.example.demo.repository;
import java.util.Optional;
import com.example.demo.model.Worker;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkerRepository extends CassandraRepository<Worker,String> {
   
        @AllowFiltering
        Optional<Worker> findBySlot(String slot);

}

