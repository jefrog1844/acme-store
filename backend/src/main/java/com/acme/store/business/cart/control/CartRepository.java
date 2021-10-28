package com.acme.store.business.cart.control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

import com.acme.store.business.store.control.LineItemEvent;
import com.acme.store.business.store.entity.Customer;
import com.acme.store.business.store.entity.LineItem;

@ApplicationScoped
public class CartRepository {

    private Map<Customer, List<LineItem>> carts = Collections.synchronizedMap(new HashMap<>());

    /**
     * Listens for a LineItemEvent from the store and places a line item
     * into the customer's cart
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
        Customer c = getCustomerEntry(customerId).orElseThrow();
        return carts.get(c);
    }

    public List<LineItem> checkout(String customerId) {
        // find the customer
        Customer c = getCustomerEntry(customerId).orElseThrow();

        // return the line items and remove the cart
        List<LineItem> cart = carts.remove(c);
        return cart;
    }

    private Optional<Customer> getCustomerEntry(String customerId) {
        Optional<Customer> optional = Optional.empty();
        for (Customer c : carts.keySet()) {
            if (c.getId().equals(customerId)) {
                optional = Optional.of(c);
                break;
            }
        }
        return optional;
    }

}
