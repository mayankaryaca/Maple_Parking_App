package com.example.maple.Models;

public class ParkingModel {
    String building_number;
    String apt_number;
    String plate_number;
    String date;
    String number_of_hours;
    String street_address;
    String geo_location_lat;
    String geo_location_lng;
    String user_id;

    public ParkingModel(String building_number, String apt_number, String plate_number, String date, String number_of_hours, String street_address, String geo_location_lat, String geo_location_lng, String user_id) {
        this.building_number = building_number;
        this.apt_number = apt_number;
        this.plate_number = plate_number;
        this.date = date;
        this.number_of_hours = number_of_hours;
        this.street_address = street_address;
        this.geo_location_lat = geo_location_lat;
        this.geo_location_lng = geo_location_lng;
        this.user_id = user_id;
    }

}
