package r2s2.planner.queueHandlers;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;

public record CityDTO(@JsonProperty("id") int id, 
		@JsonProperty("city") String city)
		implements Serializable {

}
