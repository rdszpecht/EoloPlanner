package toposervice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Landscape {
	@Id
	public String id;
	public String name;
	
	public Landscape() { }
	
	public Landscape(String name) {
		super();
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	
	
}
