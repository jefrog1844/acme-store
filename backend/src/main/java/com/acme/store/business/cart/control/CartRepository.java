package com.acme.store.business.cart.control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

import com.acme.store.business.store.control.LineItemEvent;
import com.acme.store.business.store.entity.Customer;
import com.acme.store.business.store.entity.LineItem;

@ApplicationScoped
public class CartRepository {

    private Map<Customer, List<LineItem>> carts = Collections.synchronizedMap(new HashMap<>());

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
        return carts.get(getCustomerEntry(customerId));
    }

    public List<LineItem> checkout(String customerId) {
        Customer c = getCustomerEntry(customerId);
        List<LineItem> cart = carts.remove(c);
        return cart;
    }

    private Customer getCustomerEntry(String customerId) {
        Customer customer = null;
        for (Customer c : carts.keySet()) {
            if (c.getId().equals(customerId)) {
                customer = c;
                break;
            }
        }
        return customer;
    }

}
