package r2s2.planner.serviceClients;

import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import r2s2.planner.plant.CityLandscape;


@Controller
public class TopoServiceClient {
	
    @Autowired
    private Environment env;
    
	public String getTopographicDetail(String city) {
		String topoHost = env.getProperty("TOPO_HOST");
		String topoPort = env.getProperty("TOPO_PORT");
		
		String URI = "http://" + topoHost + ":" + topoPort + "/api/topographicdetails/";
		
		RestTemplate restTemplate = new RestTemplate();
		String url = URI + city;
		CityLandscape data = restTemplate.getForObject(url, CityLandscape.class);
		
		return data.getLandscape();
	}
		
}
