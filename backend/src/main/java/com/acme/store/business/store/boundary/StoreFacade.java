package com.acme.store.business.store.boundary;

import java.util.Map;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;

import com.acme.store.business.store.control.LineItemEvent;
import com.acme.store.business.store.control.StoreRepository;
import com.acme.store.business.store.entity.Customer;
import com.acme.store.business.store.entity.LineItem;
import com.acme.store.business.store.entity.Product;
import com.acme.store.business.store.entity.PurchaseRequest;

@ApplicationScoped
public class StoreFacade {

    @Inject
    StoreRepository repository;

    @Inject
    Event<LineItemEvent> event;

    public LineItem addToCart(PurchaseRequest purchaseRequest) {

        // find the customer
        Customer customer = getCustomer(purchaseRequest.getCustomerId());

        // initiate a new line item for the selected product
        LineItem lineItem = new LineItem(getProductBySku(purchaseRequest.getSku()), 1);

        // notify listeners that a product has been selected and needs added to a cart
        this.event.fire(new LineItemEvent(customer, lineItem));

        return lineItem;
    }

    private Product getProductBySku(Long sku) {
        return getProducts().get(sku);
    }

    public Map<Long, Product> getProducts() {
        return this.repository.getProducts();
    }

    private Customer getCustomer(String customerId) {
        return repository.getCustomerById(customerId);
    }

}
