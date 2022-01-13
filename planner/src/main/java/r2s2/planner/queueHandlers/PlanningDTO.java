package r2s2.planner.queueHandlers;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PlanningDTO implements Serializable{
	
	@JsonProperty("id")
	private int id;
	@JsonProperty("city")
	private String city;
	@JsonProperty("progress")
	private int progress;
	@JsonProperty("completed")
	private boolean completed;
	@JsonProperty("planning")
	private String planning;
	
	
	
	public PlanningDTO(int id, String city, int progress, boolean completed, String planning) {
		super();
		this.id = id;
		this.city = city;
		this.progress = progress;
		this.completed = completed;
		this.planning = planning;
	}
	public int id() {
		return id;
	}
	public String city() {
		return city;
	}
	public int progress() {
		return progress;
	}
	public boolean completed() {
		return completed;
	}
	public String planning() {
		return planning;
	}

}
