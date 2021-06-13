package com.example.maple.Models;

public class User {
    private String id;
    private String email;
    private String password;
    private Boolean active;

<<<<<<< HEAD:app/src/main/java/com/example/maple/Models/User.java
    public User(String email, String password, Boolean active) {
=======

    public UserModel(String name, String email, String password, String contact, String carPlate, Boolean active) {
        this.name = name;
>>>>>>> 019cb00e19eaafdc1347966e185be9fcfad6c594:app/src/main/java/com/example/maple/Models/UserModel.java
        this.email = email;
        this.password = password;
        this.active = active;
    }

    public User() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", active=" + active +
                '}';
    }
}
