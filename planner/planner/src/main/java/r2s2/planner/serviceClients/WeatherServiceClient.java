package r2s2.planner.serviceClients;

import org.springframework.stereotype.Controller;
import net.devh.boot.grpc.client.inject.GrpcClient;

import es.grpc.Weather;
import es.grpc.WeatherServiceGrpc.WeatherServiceBlockingStub;
import es.grpc.GetWeatherRequest;

@Controller
public class WeatherServiceClient {

	@GrpcClient("weatherServer")
	private WeatherServiceBlockingStub client;
	
	public String getWeather(String city) {
		GetWeatherRequest request = GetWeatherRequest.newBuilder()
	            .setCity(city)
	            .build();
	    
		
		Weather response = client.getWeather(request);
		
	    return response.getWeather();

	}
}
