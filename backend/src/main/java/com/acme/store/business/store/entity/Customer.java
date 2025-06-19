package com.acme.store.business.store.entity;

import jakarta.json.JsonObject;

public class Customer {
    private String id;
    private String country;

    public Customer() {}

    public Customer(String id, String country) {
        this.id = id;
        this.country = country;
    }

    public Customer(JsonObject json) {
        this(json.getString("id"), json.getString("country"));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
}
