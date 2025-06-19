package com.acme.store.business.store.control;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonValue;

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

    private Map<Long, Product> products = new HashMap<>();
    private Map<String, Customer> customers = new HashMap<>();

    public StoreLoader() {
    }

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

    public Map<Long, Product> getProducts() {
        return this.products;
    }

    public Customer getCustomer(String customerId) {
        Optional<Customer> optional = customers.entrySet().stream().filter(e -> e.getKey().equals(customerId))
                .map(Map.Entry::getValue).findFirst();
        return optional.orElseThrow();
    }
}