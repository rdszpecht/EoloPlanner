package r2s2.planner.dataConsumers;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import r2s2.planner.serviceClients.TopoServiceClient;
import r2s2.planner.serviceClients.WeatherServiceClient;

@Component
public class TopologicalConsumer {

	@Autowired
	private TopoServiceClient topoServiceClient;
	
	@Async
	public CompletableFuture<String> getTopologicalData(String city) {
		return CompletableFuture.completedFuture(topoServiceClient.getTopographicDetail(city));
		
	}
	
}
