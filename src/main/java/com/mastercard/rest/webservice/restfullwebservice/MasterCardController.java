package com.mastercard.rest.webservice.restfullwebservice;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mastercard.rest.webservice.service.CardService;

@RestController
public class MasterCardController {


	   @RequestMapping(value = "/connected", produces = "application/json")
	    public Boolean getRoadCities(@RequestParam("origin") String origin, @RequestParam("destination") String destination) {

	         Boolean areCitiesAreConnected = CardService.execute(origin, destination);

	        return areCitiesAreConnected;
	    }
	
	
	
	
	

	
}
