package com.adp.coding.challenge.changemachine.model;

import java.io.Serializable;
import java.util.List;

public class Cash implements Serializable {

	private List<Coin> coins;
	private Double pendingAmount;
	private String message;

	public Cash(Double pendingAmount, List<Coin> coins) {
		this.pendingAmount = pendingAmount;
		this.coins = coins;
	}

	public List<Coin> getCoins() {
		return coins;
	}

	public void setCoins(List<Coin> coins) {
		this.coins = coins;
	}

	public Double getPendingAmount() {
		return pendingAmount;
	}

	public void setPendingAmount(Double pendingAmount) {
		this.pendingAmount = pendingAmount;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
