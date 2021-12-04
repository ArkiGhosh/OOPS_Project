package com.example.demo.model;import java.util.List;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
@AllArgsConstructor



@Table @Getter @Setter
public class User {
 @PrimaryKey 
 private @NonNull String id;
 private @NonNull String firstname;
 private @NonNull String lastname;
 private @NonNull String username;
 private @NonNull String password;
 private @NonNull String address;
 private @NonNull String email;
 private @NonNull String number;
 private @NonNull String crn;
 private float balance =1000;
 private Boolean active;
 private List<String> bookingids;
}