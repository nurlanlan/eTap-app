package com.coeus.eTap_app.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
//@NoArgsConstructor
@Data
@Entity
@Table(name = "users_eTap")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String userEmail;
    private String userPassword;

    public User(String email, String password) {
        this.userEmail = email;
        this.userEmail = password;
    }
}
