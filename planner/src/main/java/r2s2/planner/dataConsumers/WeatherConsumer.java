package r2s2.planner.dataConsumers;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import r2s2.planner.serviceClients.WeatherServiceClient;

@Component
public class WeatherConsumer {

	@Autowired
	private WeatherServiceClient weatherServiceClient;
	
	@Async
	public CompletableFuture<String> getWeatherData(String city) {
		return CompletableFuture.completedFuture(weatherServiceClient.getWeather(city));
	}
	
}
