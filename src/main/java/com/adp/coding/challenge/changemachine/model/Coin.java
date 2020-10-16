package com.adp.coding.challenge.changemachine.model;

import java.io.Serializable;

public class Coin implements Comparable<Coin>, Serializable {

	private Double coinValue;
	private Double count;

	public Coin(Double coinValue, Double count) {
		this.coinValue = coinValue;
		this.count = count;
	}

	public Double getCoinValue() {
		return coinValue;
	}

	public void setCoinValue(Double coinValue) {
		this.coinValue = coinValue;
	}

	public Double getCount() {
		return count;
	}

	public void setCount(Double count) {
		this.count = count;
	}

	@Override
	public int compareTo(Coin o) {
		return o.getCoinValue().compareTo(this.getCoinValue());
	}

}
