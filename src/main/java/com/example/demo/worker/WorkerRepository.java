package com.example.demo.worker;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkerRepository extends CassandraRepository<Worker,UUID> {
    
}
