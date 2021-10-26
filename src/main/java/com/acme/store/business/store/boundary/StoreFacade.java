package com.acme.store.business.store.boundary;

import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.json.JsonObject;

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

    public void addToCart(PurchaseRequest purchaseRequest) {
        Customer customer = repository.getCustomerById(purchaseRequest.getCustomerId());
        LineItem lineItem = new LineItem(getProductBySku(purchaseRequest.getSku()), 1);
        this.event.fire(new LineItemEvent(customer, lineItem));
    }

    private Product getProductBySku(Long sku) {
        return getProducts().get(sku);
    }

    public Map<Long, Product> getProducts() {
        return this.repository.getProducts();
    }

}
