package com.example.backend.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "users", schema = "UserManagement")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String status;

    public UserEntity(String name, String email, String status) {
        this.name = name;
        this.email = email;
        this.status = status;
    }

    public UserEntity() {

    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getStatus() {
        return status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
