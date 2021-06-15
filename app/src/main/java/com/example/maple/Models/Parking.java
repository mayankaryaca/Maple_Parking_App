package com.example.maple.Models;

import java.io.Serializable;

public class Parking implements Serializable {
    String building_number;
    String apt_number;

    String plate_number;
    String number_of_hours;
    String street_address;
    String geo_location_lat;
    String geo_location_lng;
    String user_id;
    String doc_id;

    public Parking(){

    }
    public Parking(String building_number, String apt_number, String plate_number, String number_of_hours, String street_address, String geo_location_lat, String geo_location_lng, String user_id) {
        this.building_number = building_number;
        this.apt_number = apt_number;
        this.plate_number = plate_number;
        this.number_of_hours = number_of_hours;
        this.street_address = street_address;
        this.geo_location_lat = geo_location_lat;
        this.geo_location_lng = geo_location_lng;
        this.user_id = user_id;
    }

    public Parking(String building_number, String apt_number, String plate_number, String number_of_hours, String street_address, String geo_location_lat, String geo_location_lng, String user_id, String doc_id) {
        this.building_number = building_number;
        this.apt_number = apt_number;
        this.plate_number = plate_number;
        this.number_of_hours = number_of_hours;
        this.street_address = street_address;
        this.geo_location_lat = geo_location_lat;
        this.geo_location_lng = geo_location_lng;
        this.user_id = user_id;
        this.doc_id = doc_id;
    }

    @Override
    public String toString() {
        return "Parking{" +
                "building_number='" + building_number + '\'' +
                ", apt_number='" + apt_number + '\'' +
                ", plate_number='" + plate_number + '\'' +
                ", number_of_hours='" + number_of_hours + '\'' +
                ", street_address='" + street_address + '\'' +
                ", geo_location_lat='" + geo_location_lat + '\'' +
                ", geo_location_lng='" + geo_location_lng + '\'' +
                ", user_id='" + user_id + '\'' +
                '}';
    }

    public String getBuilding_number() {
        return building_number;
    }

    public void setBuilding_number(String building_number) {
        this.building_number = building_number;
    }

    public String getApt_number() {
        return apt_number;
    }

    public void setApt_number(String apt_number) {
        this.apt_number = apt_number;
    }

    public String getPlate_number() {
        return plate_number;
    }

    public void setPlate_number(String plate_number) {
        this.plate_number = plate_number;
    }


    public String getNumber_of_hours() {
        return number_of_hours;
    }

    public void setNumber_of_hours(String number_of_hours) {
        this.number_of_hours = number_of_hours;
    }

    public String getStreet_address() {
        return street_address;
    }

    public void setStreet_address(String street_address) {
        this.street_address = street_address;
    }

    public String getGeo_location_lat() {
        return geo_location_lat;
    }

    public void setGeo_location_lat(String geo_location_lat) {
        this.geo_location_lat = geo_location_lat;
    }

    public String getGeo_location_lng() {
        return geo_location_lng;
    }

    public void setGeo_location_lng(String geo_location_lng) {
        this.geo_location_lng = geo_location_lng;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(String doc_id) {
        this.doc_id = doc_id;
    }
}
