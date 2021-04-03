package com.webservice.currency.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class XE {
	String currency;
	double rate, inv_rate;

	public XE(String currency, double rate, double inv_rate) {
		super();
		this.currency = currency;
		this.rate = rate;
		this.inv_rate = inv_rate;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(long rate) {
		this.rate = rate;
	}

	public double getInv_rate() {
		return inv_rate;
	}

	public void setInv_rate(long inv_rate) {
		this.inv_rate = inv_rate;
	}

	@Override
	public String toString() {
		return "XE [currency=" + currency + ", rate=" + rate + ", inv_rate=" + inv_rate + "]";
	}

}
