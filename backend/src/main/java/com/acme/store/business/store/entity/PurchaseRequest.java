package com.acme.store.business.store.entity;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public class PurchaseRequest {
    @NotEmpty(message = "Customer Id cannot be null or empty")
    private String customerId;

    @Max(value = 9)
    @Min(value = 1)
    private Long sku;

    public PurchaseRequest() {
    }

    public PurchaseRequest(String customerId, Long sku) {
        this.customerId = customerId;
        this.sku = sku;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Long getSku() {
        return sku;
    }

    public void setSku(Long sku) {
        this.sku = sku;
    }

}
