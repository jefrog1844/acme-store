package com.acme.store.business.cart.control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;

import com.acme.store.business.store.control.LineItemEvent;
import com.acme.store.business.store.entity.Customer;
import com.acme.store.business.store.entity.LineItem;

@ApplicationScoped
public class CartRepository {

    private Map<Customer, List<LineItem>> carts = Collections.synchronizedMap(new HashMap<>());

    /**
     * Listens for a LineItemEvent from the store and places a line item into the
     * customer's cart
     * 
     * @param event - LineItemEvent
     */
    public void addToCart(@Observes LineItemEvent event) {
        Customer customer = event.getCustomer();
        LineItem lineItem = event.getLineItem();
        if (carts.containsKey(customer)) {
            carts.get(customer).add(lineItem);
        } else {
            List<LineItem> items = new ArrayList<>();
            items.add(lineItem);
            carts.put(customer, items);
        }
    }

    public List<LineItem> getCart(String customerId) {
        // find the customer
        Optional<Customer> optional = getCustomerEntry(customerId);
        return optional.isPresent() ? carts.get(optional.get()) : new ArrayList<LineItem>();
    }

    public List<LineItem> checkout(String customerId) {
        // find the customer
        Optional<Customer> optional = getCustomerEntry(customerId);
        return optional.isPresent() ? carts.remove(optional.get()) : new ArrayList<LineItem>();
    }

    private Optional<Customer> getCustomerEntry(String customerId) {
        return carts.entrySet().stream().filter(e -> e.getKey().getId().equals(customerId)).map(Map.Entry::getKey)
                .findFirst();
    }

}
