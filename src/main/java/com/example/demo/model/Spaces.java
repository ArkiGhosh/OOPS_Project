package com.example.demo.model;
import java.util.List;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@NoArgsConstructor

@Table @Getter @Setter
public class Spaces {
 @PrimaryKey 
 private String id;
 private String space_name;
 private String location;
 private List<String> slot_num;
}