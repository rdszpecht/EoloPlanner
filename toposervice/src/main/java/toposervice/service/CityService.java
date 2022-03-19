package toposervice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import reactor.core.publisher.Mono;
import toposervice.model.City;
import toposervice.repository.CityRepository;
import toposervice.repository.LandscapeRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CityService {
	
	@Autowired
	CityRepository cityRepository;
	@Autowired
	LandscapeRepository landscapeRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(CityService.class);
	
	public Mono<City> getCityByName(String name){
		return cityRepository.findByNameIgnoreCase(name).next().switchIfEmpty(
                Mono.error(new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "City with name "+ name +" not found")))
				.flatMap(city -> {
					return this.landscapeRepository.findById(city.getLandscapeId()).flatMap(landscape -> {
						city.setLandscape(landscape);
						return Mono.just(city);
					});
				});
	}
	
	@Retryable(value = RuntimeException.class)
	void retryService(String sql) {
        logger.info("throw RuntimeException in method retryService()");
        throw new RuntimeException();
	}
}
