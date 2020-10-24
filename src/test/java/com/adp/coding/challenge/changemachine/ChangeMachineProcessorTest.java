package com.adp.coding.challenge.changemachine;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.lenient;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import com.adp.coding.challenge.changemachine.model.Coin;
import com.adp.coding.challenge.changemachine.processor.ChangeMachineProcessor;
import com.adp.coding.challenge.changemachine.repository.ChangeMachineRepository;
import com.adp.coding.challenge.changemachine.utils.WebConstants;

@ExtendWith(MockitoExtension.class)
class ChangeMachineProcessorTest {

	@Mock
	ChangeMachineRepository changeMachineRepo;

	@InjectMocks
	ChangeMachineProcessor changeMachineProcessor;

	@Mock
	JdbcTemplate jdbcTemplate;

	@Test
	void givenAmount_UpdateCoins_thenFindChange() throws Exception {

		List<Coin> coinList = new ArrayList<Coin>();
		Coin coin1 = new Coin(0.25, 100.0);
		Coin coin2 = new Coin(0.5, 100.0);
		coinList.add(coin1);
		coinList.add(coin2);

		lenient().doNothing().when(changeMachineRepo).updateCoins(coinList);

		List<Coin> coinResponse = changeMachineProcessor.findCoins(coinList, 5.0);

		assertThat(coinResponse.toString()).contains("[Coin(coinValue=0.25, count=20.0)]");
	}

	@Test
	void givenMoreAmount_thenThrowException() throws Exception {

		List<Coin> coinList = new ArrayList<Coin>();
		Coin coin1 = new Coin(0.25, 100.0);
		Coin coin2 = new Coin(0.5, 100.0);
		coinList.add(coin1);
		coinList.add(coin2);

		final String expectedMessage = WebConstants.PENDING_AMOUNT_MESSAGE;
		try {
			changeMachineProcessor.findCoins(coinList, 1200.0);
		} catch (Exception e) {
			assertThat(e.getMessage()).contains(expectedMessage);
		}
	}

}
