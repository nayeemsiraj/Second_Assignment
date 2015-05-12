package com.cse24gmail.jakir.second_assignment;

/**
 * Created by Jakir on 5/12/2015.
 */
public class Employee {
    private int id;
    private String name;
    private String title;
    private String email;
    private String address;
    private String city;
    private String country;

    public Employee() {
    }

    public Employee(int id, String name, String title, String email, String address, String city, String country) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.email = email;
        this.address = address;
        this.city = city;
        this.country = country;
    }

    public Employee(String name, String title, String email, String address, String city, String country) {
        this.name = name;
        this.title = title;
        this.email = email;
        this.address = address;
        this.city = city;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
