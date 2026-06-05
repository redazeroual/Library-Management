package com.bibliotheque.model;
public class Member {
    private int id;
    private String name;
    private String email;
    private String role;
    private String matricule;
    private String status;
    private int activeLoansCount;


    public Member(int id, String name, String email, String role, String matricule, String status, int activeLoansCount) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.matricule = matricule;
        this.status = status;
        this.activeLoansCount = activeLoansCount;
    }


    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getRole() { return role; }
    public String getMatricule() { return matricule; }
    public String getStatus() { return status; }
    public int getActiveLoansCount() { return activeLoansCount; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setRole(String role) { this.role = role; }
    public void setMatricule(String matricule) { this.matricule = matricule; }
    public void setStatus(String status) { this.status = status; }


    public int getId() { return id; }
    }
