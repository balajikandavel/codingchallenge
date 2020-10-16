package com.adp.coding.challenge.changemachine.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adp.coding.challenge.changemachine.model.Cash;
import com.adp.coding.challenge.changemachine.model.Coin;
import com.adp.coding.challenge.changemachine.processor.ChangeMachineProcessor;
import com.adp.coding.challenge.changemachine.repository.ChangeMachineRepository;
import com.adp.coding.challenge.changemachine.utils.WebConstants;
import com.google.gson.Gson;

@Service
public class ChangeMachineService {

	@Autowired
	ChangeMachineRepository coinRepository;

	@Autowired
	ChangeMachineProcessor changeMachineProcessor;

	public String getAvailableCoins() {

		Gson gson = new Gson();

		List<Coin> coinsList = coinRepository.getAvailableCoins();

		return gson.toJson(coinsList);

	}

	public void updateCoins(List<Coin> coins) {

		coinRepository.updateCoins(coins);
	}

	public String getChange(Double amount, Boolean allowLeastCoins) throws IllegalArgumentException {

		Gson gson = new Gson();
		List<Coin> coinsList = coinRepository.getAvailableCoins();

		Optional<Integer> cashAmount = WebConstants.cashList.stream().filter(a -> a.equals(amount.intValue()))
				.findAny();

		if (!cashAmount.isPresent())
			throw new IllegalArgumentException(WebConstants.AMOUNT_NOT_PRESENT_ERROR);

		if (allowLeastCoins.booleanValue())
			Collections.sort(coinsList, Collections.reverseOrder());

		Cash coinsForCash = changeMachineProcessor.calculateChange(coinsList, amount);

		return gson.toJson(coinsForCash);

	}

}
