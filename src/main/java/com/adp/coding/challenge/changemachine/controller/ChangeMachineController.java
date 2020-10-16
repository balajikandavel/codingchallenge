package com.adp.coding.challenge.changemachine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.adp.coding.challenge.changemachine.model.Coin;
import com.adp.coding.challenge.changemachine.service.ChangeMachineService;

@RestController
public class ChangeMachineController {

	@Autowired
	ChangeMachineService changeMachineService;

	@GetMapping(value = "/coins", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getAvailableCoins() {

		return changeMachineService.getAvailableCoins();
	}

	@PostMapping("/coins")
	public void updateCurrentCoins(@RequestBody List<Coin> coins) {

		changeMachineService.updateCoins(coins);
	}

	@GetMapping(value = "/change/{cash}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> getChange(@PathVariable Double cash,
			@RequestParam(name = "allowLeastCoins") Boolean allowLeastCoins) {
		try {
			String response = changeMachineService.getChange(cash, allowLeastCoins);

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
