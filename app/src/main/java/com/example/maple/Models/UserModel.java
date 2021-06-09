package com.example.maple.Models;

public class UserModel {
    private String id;
    private String name;
    private String email;
    private String password;
    private String contact;
    private String carPlate;
    private Boolean active;


    public UserModel(String name, String email, String password, String contact, String carPlate, Boolean active) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.contact = contact;
        this.carPlate = carPlate;
        this.active = active;
    }

    public UserModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getCarPlate() {
        return carPlate;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", contact='" + contact + '\'' +
                ", carPlate='" + carPlate + '\'' +
                ", active=" + active +
                '}';
    }
}
