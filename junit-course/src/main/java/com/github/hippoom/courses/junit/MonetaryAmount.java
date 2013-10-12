package com.github.hippoom.courses.junit;

import java.math.BigDecimal;

public class MonetaryAmount {

	private double amount;

	public MonetaryAmount(BigDecimal amount) {
		this.amount = amount.doubleValue();
	}

	public MonetaryAmount(String amount) {
		this.amount = new BigDecimal(amount).doubleValue();
	}

	public MonetaryAmount add(MonetaryAmount amount) {
		if (amount == null) {
			return this;
		}
		return new MonetaryAmount(aCalculable(getAmount()).add(
				aCalculable(amount.getAmount())));
	}

	public double getAmount() {
		return amount;
	}

	private BigDecimal aCalculable(double amount) {
		return new BigDecimal(Double.toString(amount));
	}

	public MonetaryAmount multiply(int quantity) {
		return new MonetaryAmount(aCalculable(getAmount()).multiply(
				new BigDecimal(quantity)));
	}

	public BigDecimal getBigDecimalValue() {
		return aCalculable(getAmount());
	}

}
