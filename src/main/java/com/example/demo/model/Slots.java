package com.example.demo.model;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
@NoArgsConstructor



@Table @Getter @Setter
public class Slots {
 @PrimaryKey 
 private @NonNull String id;
 private @NonNull String slotnum;
 private @NonNull String space;
 private String workerid;

}