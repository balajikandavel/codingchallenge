package com.adp.coding.challenge.changemachine;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.adp.coding.challenge.changemachine.model.Cash;
import com.adp.coding.challenge.changemachine.model.Coin;
import com.adp.coding.challenge.changemachine.processor.ChangeMachineProcessor;
import com.adp.coding.challenge.changemachine.repository.ChangeMachineRepository;
import com.adp.coding.challenge.changemachine.service.ChangeMachineService;

@ExtendWith(MockitoExtension.class)

class ChangeMachineApplicationTests {

	@Mock
	ChangeMachineRepository changeMachineRepo;

	@InjectMocks
	ChangeMachineService changeMachineService = new ChangeMachineService();

	@Mock
	ChangeMachineProcessor changeMachineProcessor;

	@Test
	void givenWrongAmount_thenThrowError() throws Exception {

		List<Coin> response = new ArrayList<Coin>();
		Coin coin1 = new Coin(0.1, 100.0);
		Coin coin2 = new Coin(0.5, 100.0);
		response.add(coin1);
		response.add(coin2);

		Mockito.when(changeMachineRepo.getAvailableCoins()).thenReturn(response);

		String expectedMessage = "Given amount is not valid";
		try {
			changeMachineService.getChange(23.0, false);
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage()).contains(expectedMessage);

		}

	}

	@Test
	void givenAmount_WhenGetChange_thenProvideChange() throws Exception {

		List<Coin> coinList = new ArrayList<Coin>();
		Coin coin1 = new Coin(0.1, 100.0);
		Coin coin2 = new Coin(0.5, 100.0);
		coinList.add(coin1);
		coinList.add(coin2);

		Cash coinsForCash = new Cash(0.0, coinList);

		Mockito.when(changeMachineRepo.getAvailableCoins()).thenReturn(coinList);

		Mockito.when(changeMachineProcessor.calculateChange(coinList, 5.0)).thenReturn(coinsForCash);

		String result = changeMachineService.getChange(5.0, false);

		System.out.println(result);
		assertThat(result).contains("pendingAmount\":0.0");

	}

}
