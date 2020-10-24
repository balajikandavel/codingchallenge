package com.adp.coding.challenge.changemachine.processor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adp.coding.challenge.changemachine.model.Coin;
import com.adp.coding.challenge.changemachine.repository.ChangeMachineRepository;
import com.adp.coding.challenge.changemachine.utils.WebConstants;

@Component
public class ChangeMachineProcessor {

	@Autowired
	ChangeMachineRepository changeMachineRepository;

	public List<Coin> findCoins(List<Coin> coinList, Double amount) throws Exception {

		List<Coin> remainingCoinsList = new ArrayList<>();
		List<Coin> changeToReturnList = new ArrayList<>();
		Double pendingAmount = null;
		Coin remainingCoins;
		Coin changeToReturn;

		for (Coin coin : coinList) {
			pendingAmount = amount - (coin.getCount() * coin.getCoinValue());

			if (pendingAmount <= 0) {
				Double count = coin.getCount() - Math.abs((pendingAmount / coin.getCoinValue()));
				changeToReturn = new Coin(coin.getCoinValue(), count);
				remainingCoins = new Coin(coin.getCoinValue(), Math.abs(pendingAmount / coin.getCoinValue()));
				pendingAmount = 0.0;
			} else {
				changeToReturn = new Coin(coin.getCoinValue(), coin.getCount());
				remainingCoins = new Coin(coin.getCoinValue(), 0.0);
			}
			remainingCoinsList.add(remainingCoins);
			changeToReturnList.add(changeToReturn);

			if (pendingAmount == 0) {
				break;
			}
		}
		if (pendingAmount > 0) {
			throw new Exception(WebConstants.PENDING_AMOUNT_MESSAGE);
		}

		if (pendingAmount == 0) {
			changeMachineRepository.updateCoins(remainingCoinsList);
		}
		return changeToReturnList;
	}
}
