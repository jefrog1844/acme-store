package com.acme.store.business.store.entity;

import java.math.BigDecimal;

import jakarta.json.JsonObject;

public class Product {

    private Long sku;
    private String description;
    private BigDecimal price;
    private String countryOfOrigin;
    private boolean isExempt;

    public Product() {
    }

    public Product(Long sku, String description, BigDecimal price, String countryOfOrigin, boolean isExempt) {
        this.sku = sku;
        this.description = description;
        this.price = price;
        this.countryOfOrigin = countryOfOrigin;
        this.isExempt = isExempt;
    }

    public Product(JsonObject json) {
        this(Long.valueOf(json.getString("sku")), json.getString("description"), json.getJsonNumber("price").bigDecimalValue(),
                json.getString("countryOfOrigin"), json.getBoolean("exempt"));
    }

    public Long getSku() {
        return sku;
    }

    public void setSku(Long sku) {
        this.sku = sku;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public void setExempt(boolean isExempt) {
        this.isExempt = isExempt;
    }

    public String description() {
        return this.description;
    }

    public BigDecimal price() {
        return this.price;
    }

    public String countryOfOrigin() {
        return this.countryOfOrigin;
    }

    public boolean isExempt() {
        return this.isExempt;
    }

    @Override
    public String toString() {
        return "Product [countryOfOrigin=" + countryOfOrigin + ", description=" + description + ", isExempt=" + isExempt
                + ", price=" + price + ", sku=" + sku + "]";
    }

}
