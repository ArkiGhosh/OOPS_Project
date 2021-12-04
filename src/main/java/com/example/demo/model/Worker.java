package com.example.demo.model;
import java.util.List;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@NoArgsConstructor
@Table @Getter @Setter
public class Worker {
 @PrimaryKey
 private @NonNull String id;
 private @NonNull String name;
 private @NonNull String space;
 private @NonNull String slot;
 private @NonNull String service;
 private int hours_worked = 0;
 private float avg_rating = 0;
 private int no_of_ratings = 0;
 private int total_ratings = 0;
 private List<String> comments;  
 private List<String> bookingids;
}