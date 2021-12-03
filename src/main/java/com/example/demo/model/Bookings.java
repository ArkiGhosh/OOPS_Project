package com.example.demo.model;

import java.util.List;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
@AllArgsConstructor



@Table @Getter @Setter
public class Bookings {
 @PrimaryKey 
 private @NonNull String id;
 private @NonNull String slotid;
 private @NonNull String space;
 private int cost;
 private int intime;
 private int outtime;
 private String date;
 private List<String> users;
}