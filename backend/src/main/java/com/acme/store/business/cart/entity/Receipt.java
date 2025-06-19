package com.acme.store.business.cart.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import com.acme.store.business.cart.control.ImportTaxCalculator;
import com.acme.store.business.cart.control.SalesTaxCalculator;
import com.acme.store.business.store.entity.LineItem;

public class Receipt {

    private String customerId;
    private List<LineItem> items = new ArrayList<>();
    private SalesTaxCalculator salesTaxCalculator;
    private ImportTaxCalculator importTaxCalculator;

    public Receipt() {
    }

    public Receipt(String customerId, List<LineItem> items, SalesTaxCalculator salesTaxCalculator,
            ImportTaxCalculator importTaxCalculator) {
        this.customerId = customerId;
        this.items = items;
        this.salesTaxCalculator = salesTaxCalculator;
        this.importTaxCalculator = importTaxCalculator;
    }

    public String getCustomerId() {
        return customerId;
    }

    public List<LineItem> getItems() {
        return items;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setItems(List<LineItem> items) {
        this.items = items;
    }

    public SalesTaxCalculator getSalesTaxCalculator() {
        return salesTaxCalculator;
    }

    public void setSalesTaxCalculator(SalesTaxCalculator salesTaxCalculator) {
        this.salesTaxCalculator = salesTaxCalculator;
    }

    public ImportTaxCalculator getImportTaxCalculator() {
        return importTaxCalculator;
    }

    public void setImportTaxCalculator(ImportTaxCalculator importTaxCalculator) {
        this.importTaxCalculator = importTaxCalculator;
    }

    public BigDecimal totalCost() {
        BigDecimal totalCost = BigDecimal.valueOf(0.0);
        for (LineItem li : items) {
            BigDecimal finalPrice = li.getProduct().getPrice().add(li.getTax(salesTaxCalculator, importTaxCalculator));
            totalCost = totalCost.add(finalPrice);
        }
        return totalCost.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal totalTax() {
        BigDecimal totalTax = BigDecimal.valueOf(0.0);
        for (LineItem li : items) {
            totalTax = totalTax.add(li.getTax(salesTaxCalculator, importTaxCalculator));
        }
        return totalTax.setScale(2, RoundingMode.HALF_UP);
    }

    public String print() {
        StringBuilder sb = new StringBuilder();
        sb.append(System.lineSeparator());
        for (LineItem li : items) {
            sb.append(li.getQuantity());
            sb.append(" ");
            sb.append(li.getProduct().description());
            sb.append(": ");
            BigDecimal tax = li.getTax(salesTaxCalculator, importTaxCalculator);
            sb.append(li.getProduct().getPrice().add(tax));
            sb.append(System.lineSeparator());
        }
        sb.append(System.lineSeparator());
        sb.append("Sales Taxes: " + totalTax());
        sb.append(System.lineSeparator());
        sb.append("Total: " + totalCost());
        sb.append(System.lineSeparator());
        return sb.toString();
    }

}
