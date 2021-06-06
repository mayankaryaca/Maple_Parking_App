package com.example.maple.Models;

import java.io.Serializable;

public class User implements Serializable {
    int id;
    String name;
    String email;
    String password;
    String contact;
    String carPlate;

    public User(String name, String email, String password, String contact, String carPlate) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.contact = contact;
        this.carPlate = carPlate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", contact='" + contact + '\'' +
                ", carPlate='" + carPlate + '\'' +
                '}';
    }
}
