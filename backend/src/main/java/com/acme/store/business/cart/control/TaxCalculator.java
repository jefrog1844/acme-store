package com.acme.store.business.cart.control;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.eclipse.microprofile.config.ConfigProvider;

public abstract class TaxCalculator implements Calculator {

    private static final BigDecimal INCREMENT = ConfigProvider.getConfig().getValue("com.acme.store.tax.rounding",
            BigDecimal.class);

    public TaxCalculator() {
    }

    public BigDecimal calculate(BigDecimal price) {
        BigDecimal tax = price.multiply(taxRate());
        return round(tax);
    }

    protected abstract BigDecimal taxRate();

    private BigDecimal round(BigDecimal value) {
        if (INCREMENT.signum() == 0) {
            // 0 increment does not make much sense, but prevent division by 0
            return value;
        } else {
            BigDecimal divided = value.divide(INCREMENT, 0, RoundingMode.UP);
            BigDecimal result = divided.multiply(INCREMENT);
            return result;
        }
    }

}
