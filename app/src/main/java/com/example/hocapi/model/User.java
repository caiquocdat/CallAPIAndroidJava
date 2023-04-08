package com.example.hocapi.model;

import com.google.gson.Gson;

import java.util.List;

public class User {
    private int id;
    private String name;
    private Address address;
    private List<jobs> jobs;

    public User(int id, String name, Address address, List<com.example.hocapi.model.jobs> jobs) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.jobs = jobs;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<com.example.hocapi.model.jobs> getJobs() {
        return jobs;
    }

    public void setJobs(List<com.example.hocapi.model.jobs> jobs) {
        this.jobs = jobs;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", jobs=" + jobs +
                '}';
    }
    public String toJSON(){
        Gson gson= new Gson();
        return gson.toJson(this);
    }
}
