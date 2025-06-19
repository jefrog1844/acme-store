package com.acme.store.business.cart.control;

import java.math.BigDecimal;

import jakarta.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.config.ConfigProvider;

@ApplicationScoped
public class ImportTaxCalculator extends TaxCalculator {

  private static final BigDecimal TAX_RATE = ConfigProvider.getConfig().getValue("com.acme.store.tax.import",
      BigDecimal.class);

  public ImportTaxCalculator() {
  }

  @Override
  protected BigDecimal taxRate() {
    return TAX_RATE;
  }

}
