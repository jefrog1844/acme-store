package com.acme.store.business.cart.control;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class ImportTaxCalculator implements Calculator {

  @Inject
  @ConfigProperty(name = "com.acme.store.tax.import")
  BigDecimal taxRate;

  public ImportTaxCalculator() {
  }

  public BigDecimal calculate(BigDecimal price) {
    BigDecimal tax = price.multiply(taxRate);
    return round(tax);
  }

  public BigDecimal getTaxRate() {
    return taxRate;
  }

  public void setTaxRate(BigDecimal taxRate) {
    this.taxRate = taxRate;
  }

  private BigDecimal round(BigDecimal value) {
    BigDecimal inc = new BigDecimal(.05);
    if (inc.signum() == 0) {
      // 0 increment does not make much sense, but prevent division by 0
      return value;
    } else {
      BigDecimal divided = value.divide(inc, 0, RoundingMode.UP);
      BigDecimal result = divided.multiply(inc);
      return result;
    }
  }

}
