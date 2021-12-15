package toposervice.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import toposervice.model.Landscape;

@Repository
public interface LandscapeRepository extends ReactiveCrudRepository<Landscape, String> {

}

