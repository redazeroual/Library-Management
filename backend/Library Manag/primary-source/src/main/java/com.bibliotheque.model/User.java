package com.bibliotheque.model;

public class User {
    private int id;
    private String username;
    private String fullName;
    private String password;


    public User(int id, String username, String fullName) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.password = "";
    }


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getPassword() { return  password; }
    public void setPassword(String password) { this.password = password; }
    }

