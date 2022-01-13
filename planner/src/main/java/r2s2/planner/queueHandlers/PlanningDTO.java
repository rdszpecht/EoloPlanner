package r2s2.planner.queueHandlers;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;

public record PlanningDTO(@JsonProperty("id") int id,
		@JsonProperty("city") String city,
		@JsonProperty("progress") int progress,
		@JsonProperty("completed") boolean completed,
		@JsonProperty("planning") String planning) implements Serializable{

}
