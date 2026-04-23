package resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

@Path("/")
public class Resource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> discovery() {
        Map<String, Object> response = new HashMap<>();
        response.put("version", "1.0");
        response.put("description", "Smart Campus Sensor API");
        response.put("contact", "@w12345");

        Map<String, String> resources = new HashMap<>();
        resources.put("rooms", "/api/v1/rooms");
        resources.put("sensors", "/api/v1/sensors");

        response.put("resources", resources);
        return response;
    }
}