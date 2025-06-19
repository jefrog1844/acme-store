package com.acme.store.business.cart.boundary;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import com.acme.store.business.cart.control.CartRepository;
import com.acme.store.business.cart.control.ImportTaxCalculator;
import com.acme.store.business.cart.control.SalesTaxCalculator;
import com.acme.store.business.cart.entity.Receipt;
import com.acme.store.business.store.entity.LineItem;

@ApplicationScoped
public class CartFacade {
    
    @Inject
    CartRepository repository;

    @Inject
    SalesTaxCalculator salesTaxCalculator;

    @Inject
    ImportTaxCalculator importTaxCalculator;

    public List<LineItem> getCart(String customerId) {
        return repository.getCart(customerId);
    }
    
    public Receipt checkout(String customerId) {
        List<LineItem> cartItems = repository.checkout(customerId);
        Receipt receipt = new Receipt(customerId, cartItems, salesTaxCalculator, importTaxCalculator);
        return receipt;
    }
}
