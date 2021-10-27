package com.acme.store.business.store.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.acme.store.business.cart.control.ImportTaxCalculator;
import com.acme.store.business.cart.control.SalesTaxCalculator;

public class LineItem {

    private Product product;
    private int quantity;

    public LineItem() {
    }

    public LineItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }


    public int getQuantity() {
        return quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTax(SalesTaxCalculator salesTaxCalculator, ImportTaxCalculator importTaxCalculator) {
        BigDecimal salesTax = BigDecimal.valueOf(0.0);
        BigDecimal importTax = BigDecimal.valueOf(0.0);

        if (!getProduct().isExempt()) {
            salesTax = salesTaxCalculator.calculate(getProduct().price());
        }

        if (!getProduct().countryOfOrigin().equals("USA")) {
            importTax = importTaxCalculator.calculate(getProduct().price());
        }

        return salesTax.add(importTax).setScale(2, RoundingMode.HALF_UP);
    }

}
