package com.acme.store.business.cart.control;

import java.math.BigDecimal;

public interface Calculator {
    public BigDecimal calculate(BigDecimal price);
}
