package com.example.maple.Models;

public class Profile  {
    private String id;
    private String firstName;
    private String lastName;
    private String contact;
    private String carPlate;

    public Profile(String firstName, String lastName, String contact, String carPlate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.contact = contact;
        this.carPlate = carPlate;
    }

    public Profile() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    @Override
    public String toString() {
        return "Profile{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", contact='" + contact + '\'' +
                ", carPlate='" + carPlate + '\'' +
                '}';
    }
}
