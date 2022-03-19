package toposervice.service;

import java.util.Random;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Retryable;
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
	
	private static final Logger logger = LoggerFactory.getLogger(DataService.class);
	
	@PostConstruct
	public void init() {
		Landscape flat = new Landscape("flat");
		Landscape mountain = new Landscape("mountain");
		
		Mono.just(flat).flatMap(this.landscapeRepository::save).block();
		Mono.just(mountain).flatMap(this.landscapeRepository::save).block();
		
		
		Landscape[] landscapes = new Landscape[] {flat, mountain};
		
		String[] cities = new String[]{"Madrid", "Barcelona", "Jaca", "Andorra", "Valencia", "Sevilla", "Zaragoza", "Málaga", "Murcia", "Palma",
		"Bilbao", "Alicante", "Córdoba", "Valladolid", "Vigo", "Gijón", "Vitoria"};
		for(String city: cities) {
			Mono.just(new City(city, landscapes[new Random().nextInt(landscapes.length)])).flatMap(this.cityRepository::save).block();
		}
	}
	
	@Retryable(value = RuntimeException.class)
	void retryService(String sql) {
        logger.info("throw RuntimeException in method retryService()");
        throw new RuntimeException();
	}
}
