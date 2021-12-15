package toposervice.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import toposervice.model.City;

@Repository
public interface CityRepository extends ReactiveCrudRepository<City, String> {
	Flux<City> findByName(String name);
}
