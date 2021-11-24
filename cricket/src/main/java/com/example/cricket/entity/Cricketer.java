package com.example.cricket.entity;


import org.springframework.stereotype.Component;

@Component
public class Cricketer {
    private int id;
    private String name;
    private String role;

    @Override
    public String toString() {
        return "Cricketer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    public Cricketer(int id, String name, String role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public Cricketer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
