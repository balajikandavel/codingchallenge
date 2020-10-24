package com.adp.coding.challenge.changemachine.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorTO {

	public String message;

	public ErrorTO(String message) {
		this.message = message;
	}
}
