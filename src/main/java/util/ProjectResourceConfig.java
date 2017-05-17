package util;

import org.glassfish.jersey.server.ResourceConfig;

public class ProjectResourceConfig extends ResourceConfig{
	public ProjectResourceConfig() {
		packages("service");
	}
}
