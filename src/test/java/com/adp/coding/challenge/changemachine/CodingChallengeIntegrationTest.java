package com.adp.coding.challenge.changemachine;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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

		assertThat(getCoins).contains("coinValue");
	}

	@Test
	void getChangeTest() throws Exception {

		String getChange = changeMachineService.getChange(5.0, false);

		assertThat(getChange).contains("pendingAmount\":0.0");
	}

}
