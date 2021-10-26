package com.acme.store.business.store.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;

import com.acme.store.business.FileManager;
import com.acme.store.business.store.entity.Customer;
import com.acme.store.business.store.entity.Product;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class StoreLoader {

    @Inject
    FileManager fileManager;

    @Inject
    @ConfigProperty(name = "app.products")
    String productsFile;

    @Inject
    @ConfigProperty(name = "app.customers")
    String customersFile;

    private Map<Long,Product> products = new HashMap<>();
    private Map<String,Customer> customers = new HashMap<>();
    
    public StoreLoader() {}

    public void onStartup(@Observes @Initialized(ApplicationScoped.class) final Object event) {
        loadProducts();
        loadCustomers();        
    }

    private void loadProducts() {
        JsonArray objects = fileManager.loadArray(productsFile);
        parseProducts(objects);
    }

    private void loadCustomers() {
        JsonArray objects = fileManager.loadArray(customersFile);
        parseCustomers(objects);
    }

    private void parseProducts(JsonArray array) {
        for (JsonValue value : array) {
            JsonObject json = value.asJsonObject();
            Product p = new Product(json);
            this.products.put(p.getSku(), p);
        }
    }

    private void parseCustomers(JsonArray array) {
        for (JsonValue value : array) {
            JsonObject json = value.asJsonObject();
            Customer c = new Customer(json);
            this.customers.put(c.getId(), c);
        }
    }

    public Map<Long,Product> getProducts() {
        return this.products;
    }

    public Customer getCustomer(String customerId) {
        return customers.get(customerId);
    }
}