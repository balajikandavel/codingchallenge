package com.adp.coding.challenge.changemachine.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Coin implements Comparable<Coin>, Serializable {

	private Double coinValue;
	private Double count;

	public Coin(Double coinValue, Double count) {
		this.coinValue = coinValue;
		this.count = count;
	}

	@Override
	public int compareTo(Coin o) {
		return o.getCoinValue().compareTo(this.getCoinValue());
	}

}
