package r2s2.planner.serviceClients;

import org.springframework.stereotype.Controller;
import net.devh.boot.grpc.client.inject.GrpcClient;

import es.grpc.Weather;
import es.grpc.GetWeatherRequest;
import es.grpc.WeatherServiceGrpc.WeatherServiceBlockingStub;

@Controller
public class WeatherServiceClient {

	@GrpcClient("weatherServer")
	private WeatherServiceBlockingStub client;
	
	public String getWeather(String city) {
		WeatherRequest request = WeatherRequest.newBuilder()
	            .setCity(city)
	            .build();
	        
		WeatherResponse response = client.weather(request);
		

	    return response;

	}
}
