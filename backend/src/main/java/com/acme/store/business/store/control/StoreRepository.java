package com.acme.store.business.store.control;

import java.util.Map;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import com.acme.store.business.store.entity.Customer;
import com.acme.store.business.store.entity.Product;

@ApplicationScoped
public class StoreRepository {

    @Inject
    StoreLoader loader;

    public Customer getCustomerById(String customerId) {
        return this.loader.getCustomer(customerId);
    }

    public Map<Long, Product> getProducts() {
        return this.loader.getProducts();
    }
    
}
