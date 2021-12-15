package toposervice.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;
import toposervice.model.City;
import toposervice.model.Landscape;
import toposervice.repository.CityRepository;
import toposervice.repository.LandscapeRepository;

@Service
public class DataService {
	
	@Autowired
	CityRepository cityRepository;
	
	@Autowired
	LandscapeRepository landscapeRepository;
	
	@PostConstruct
	public void init() {
		Landscape flat = new Landscape("flat");
		Landscape mountain = new Landscape("mountain");
		
		Mono.just(flat).flatMap(this.landscapeRepository::save).block();
		Mono.just(mountain).flatMap(this.landscapeRepository::save).block();
		
		Mono.just(new City("Madrid", flat)).flatMap(this.cityRepository::save).block();
		Mono.just(new City("Gijon", mountain)).flatMap(this.cityRepository::save).block();
		Mono.just(new City("Santander", mountain)).flatMap(this.cityRepository::save).block();
		Mono.just(new City("Cordoba", flat)).flatMap(this.cityRepository::save).block();
	}
}
