package toposervice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import toposervice.model.City;
import toposervice.repository.CityRepository;
import toposervice.repository.LandscapeRepository;

@Service
public class CityService {
	
	@Autowired
	CityRepository cityRepository;
	@Autowired
	LandscapeRepository landscapeRepository;
	
	public Mono<City> getCityByName(String name){
		return cityRepository.findByName(name).next().switchIfEmpty(
                Mono.error(new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "City with name "+ name +" not found")))
				.flatMap(city -> {
					return this.landscapeRepository.findById(city.getLandscapeId()).flatMap(landscape -> {
						city.setLandscape(landscape);
						return Mono.just(city);
					});
				});
	}
}
