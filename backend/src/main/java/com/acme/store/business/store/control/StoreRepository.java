package com.acme.store.business.store.control;

import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

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
