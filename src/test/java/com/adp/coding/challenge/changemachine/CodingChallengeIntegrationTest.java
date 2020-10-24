package com.adp.coding.challenge.changemachine;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.adp.coding.challenge.changemachine.model.Coin;
import com.adp.coding.challenge.changemachine.service.ChangeMachineService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class CodingChallengeIntegrationTest {

	@Autowired
	private ChangeMachineService changeMachineService;

	@Test
	void getCoinsTest() throws Exception {

		String getCoins = changeMachineService.getAvailableCoins();

		assertThat(getCoins).contains(
				"[{\"coinValue\":0.25,\"count\":100.0},{\"coinValue\":0.1,\"count\":100.0},{\"coinValue\":0.05,\"count\":100.0},{\"coinValue\":0.01,\"count\":100.0}]");
	}

	@Test
	void getChangeTest() throws Exception {

		List<Coin> getChange = changeMachineService.getChange(20.0, false);

		assertThat(getChange.toString()).contains("[Coin(coinValue=0.25, count=80.0)]");
	}

}
