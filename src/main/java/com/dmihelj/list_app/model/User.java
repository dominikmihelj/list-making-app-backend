package com.dmihelj.list_app.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

public class User {
    private int id;
    private String username;
    private String password; // Password should be hashed for security

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
