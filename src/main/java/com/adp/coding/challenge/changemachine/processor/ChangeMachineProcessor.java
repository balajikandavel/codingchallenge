package com.adp.coding.challenge.changemachine.processor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adp.coding.challenge.changemachine.model.Cash;
import com.adp.coding.challenge.changemachine.model.Coin;
import com.adp.coding.challenge.changemachine.repository.ChangeMachineRepository;
import com.adp.coding.challenge.changemachine.utils.WebConstants;

@Component
public class ChangeMachineProcessor {

	@Autowired
	ChangeMachineRepository coinRepository;

	public Cash calculateChange(List<Coin> availableCoins, Double amount) {

		Cash cash = new Cash(amount, new ArrayList<>());
		List<Coin> coinList = new ArrayList<>();
		for (Coin coin : availableCoins) {
			if (cash.getPendingAmount() == 0)
				break;
			findCoinsQuantity(coin, cash, coinList);
		}

		if (cash.getPendingAmount() > 0)
			cash.setMessage(WebConstants.PENDING_AMOUNT_MESSAGE);

		return cash;
	}

	private Cash findCoinsQuantity(Coin coin, Cash coinsForCash, List<Coin> coinList) {
		List<Coin> remainingCoinsList = new ArrayList<>();
		Coin remainingCoins;
		Double pendingAmount = coinsForCash.getPendingAmount() - (coin.getCount() * coin.getCoinValue());

		if (pendingAmount < 0) {
			Double count = coin.getCount() - Math.abs((pendingAmount / coin.getCoinValue()));
			getCoinsForCash(coinsForCash, coin.getCoinValue(), count, coinList, 0.0);
			remainingCoins = new Coin(coin.getCoinValue(), Math.abs(pendingAmount / coin.getCoinValue()));
		} else {
			getCoinsForCash(coinsForCash, coin.getCoinValue(), coin.getCount(), coinList, pendingAmount);
			remainingCoins = new Coin(coin.getCoinValue(), 0.0);
		}
		remainingCoinsList.add(remainingCoins);
		coinRepository.updateCoins(remainingCoinsList);

		return coinsForCash;
	}

	private Cash getCoinsForCash(Cash coinsForCash, Double coinValue, Double count, List<Coin> coinList,
			Double pendingAmount) {

		Coin forCashCoin = new Coin(coinValue, count);
		coinList.add(forCashCoin);
		coinsForCash.setCoins(coinList);
		coinsForCash.setPendingAmount(pendingAmount);

		return coinsForCash;
	}

}
