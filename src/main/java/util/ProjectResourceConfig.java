package util;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("api")
public class ProjectResourceConfig extends ResourceConfig{
	public ProjectResourceConfig() {
		packages("service");
	}
}
