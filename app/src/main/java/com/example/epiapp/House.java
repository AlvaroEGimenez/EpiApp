package com.example.epiapp;

public class House {

    private Double latitude;
    private Double longitude;
    private String neighborhood;
    private String city;
    private int telephone;
    private int residents;
    private String surname;
    private String description;

    public House() {
    }

    public House(Double latitude, Double longitude, String neighborhood, String city, int telephone, int residents, String surname, String description) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.neighborhood = neighborhood;
        this.city = city;
        this.telephone = telephone;
        this.residents = residents;
        this.surname = surname;
        this.description = description;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public int getResidents() {
        return residents;
    }

    public void setResidents(int residents) {
        this.residents = residents;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
