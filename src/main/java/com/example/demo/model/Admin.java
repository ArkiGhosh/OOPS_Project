package com.example.demo.model;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;


@AllArgsConstructor


@Table @Getter @Setter
public class Admin {

    @PrimaryKey
    private @NonNull String id;
    private @NonNull String firstname;
    private @NonNull String lastname;
    private @NonNull String username;
    private @NonNull String password;
    
}
