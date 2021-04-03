package com.webservice.currency;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webservice.currency.domain.Passanger;

@RestController
public class CurrencyController {

	@GetMapping("/passanger")
	public Passanger getAirline() {
		Passanger air = new Passanger();
		air.setName("Kunal");
		air.setName("Mumbai");

		return air;

	}

}
