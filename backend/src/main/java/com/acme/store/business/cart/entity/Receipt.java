package com.acme.store.business.cart.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.annotation.JsonbTransient;
import javax.json.bind.config.PropertyNamingStrategy;
import javax.json.bind.config.PropertyOrderStrategy;

import com.acme.store.business.cart.control.ImportTaxCalculator;
import com.acme.store.business.cart.control.SalesTaxCalculator;
import com.acme.store.business.store.entity.LineItem;

public class Receipt {

    private String customerId;
    private List<LineItem> items = new ArrayList<>();
    private SalesTaxCalculator salesTaxCalculator;
    private ImportTaxCalculator importTaxCalculator;
    private BigDecimal totalTax = BigDecimal.valueOf(0.0);
    private BigDecimal totalCost = BigDecimal.valueOf(0.0);

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

    public BigDecimal getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(BigDecimal totalTax) {
        this.totalTax = totalTax;
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

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public String print() {
        StringBuilder sb = new StringBuilder();
        sb.append("Output ").append(customerId).append(":");
        sb.append(System.lineSeparator());
        for (LineItem li : items) {
            sb.append(li.getQuantity());
            sb.append(" ");
            sb.append(li.getProduct().description());
            sb.append(": ");
            BigDecimal finalPrice = li.getFinalPrice(salesTaxCalculator, importTaxCalculator).setScale(2,
                    RoundingMode.HALF_UP);
            totalCost = totalCost.add(finalPrice).setScale(2, RoundingMode.HALF_UP);
            totalTax = totalTax.add(finalPrice).subtract(li.getProduct().getPrice()).setScale(2, RoundingMode.HALF_UP);
            sb.append(finalPrice);
            sb.append(System.lineSeparator());
        }
        sb.append("Sales Taxes: " + totalTax);
        sb.append(System.lineSeparator());
        sb.append("Total: " + totalCost);
        sb.append(System.lineSeparator());
        return sb.toString();
    }

}
