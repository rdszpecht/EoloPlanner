package toposervice.controller;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
import toposervice.model.City;
import toposervice.model.CityDTO;
import toposervice.service.CityService;

@RestController
@RequestMapping("/api/topographicdetails")
public class TopographicDetails {
	
	final long minDelay = 1000;
	final long maxDelay = 3000;
	
	@Autowired
	CityService cityService;
	
	@GetMapping("/{name}")
	public Mono<CityDTO> getCityDetails(@PathVariable String name){
		Duration delay = Duration.ofMillis((long) (Math.random()*(maxDelay-minDelay+1)+minDelay));
		return this.cityService.getCityByName(name).delayElement(delay).map(this::toCityDTO);
	}
	
	private CityDTO toCityDTO(City city) {
		return new CityDTO(city);
	}
}
