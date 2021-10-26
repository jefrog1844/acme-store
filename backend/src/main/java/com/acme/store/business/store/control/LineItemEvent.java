package com.acme.store.business.store.control;

import com.acme.store.business.store.entity.Customer;
import com.acme.store.business.store.entity.LineItem;

public class LineItemEvent {
    private LineItem lineItem;
    private Customer customer;

    public LineItemEvent(Customer customer, LineItem lineItem) {
        this.customer = customer;
        this.lineItem = lineItem;
    }

    public Customer getCustomer() {
        return customer;
    }

    public LineItem getLineItem() {
        return lineItem;
    }
}
