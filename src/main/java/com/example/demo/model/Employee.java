package com.example.demo.model;import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
@AllArgsConstructor



@Table @Getter @Setter
public class Employee {
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
 private Boolean active;
}