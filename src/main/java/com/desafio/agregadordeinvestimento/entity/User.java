package com.desafio.agregadordeinvestimento.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name ="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;


    @Column(name = "password")
    private String password;


    @CreationTimestamp
    private Instant creationTimestemp;



    @UpdateTimestamp
    private Instant updatedTimestemp;

    public User() {
    }

    public User(UUID userId, String username, String email, String password, Instant creationTimestemp, Instant updatedTimestemp) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.creationTimestemp = creationTimestemp;
        this.updatedTimestemp = updatedTimestemp;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Instant getCreationTimestemp() {
        return creationTimestemp;
    }

    public void setCreationTimestemp(Instant creationTimestemp) {
        this.creationTimestemp = creationTimestemp;
    }

    public Instant getUpdatedTimestemp() {
        return updatedTimestemp;
    }

    public void setUpdatedTimestemp(Instant updatedTimestemp) {
        this.updatedTimestemp = updatedTimestemp;
    }
}
