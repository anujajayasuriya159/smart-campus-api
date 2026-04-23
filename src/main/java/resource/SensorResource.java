package resource;

import model.Sensor;
import model.Room;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.*;
import java.util.logging.Logger;

@Path("/sensors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SensorResource {

    public static Map<String, Sensor> sensorsMap = new HashMap<>();
    private static Map<String, Room> rooms = RoomResource.roomMap; // reuse rooms
    private static final Logger logger = Logger.getLogger(SensorResource.class.getName());

    //GET the sensors
    @GET
    public Collection<Sensor> getAllSensors(@QueryParam("type") String type) {

        //If not filtering by type, return all sensors
        if (type == null) {
            return sensorsMap.values();
        }

        //If filtering by type
        List<Sensor> filtered = new ArrayList<>();

        for (Sensor sensor : sensorsMap.values()) {
            if (sensor.getType().equalsIgnoreCase(type)) {
                filtered.add(sensor);
            }
        }

        return filtered;
    }

    //POST create a sensor(Room validation)
    @POST
    public Object createSensor(Sensor sensor) {

        logger.info("Creating a Sensor: " + sensor.getId());
        
        //Validation
        if (!rooms.containsKey(sensor.getRoomId())) {
            throw new exception.LinkedResourceNotFoundException("The selected room does not exist");
        }

        sensorsMap.put(sensor.getId(), sensor);

        //Linking the sensors to the rooms
        Room room = rooms.get(sensor.getRoomId());
        room.getSensorIds().add(sensor.getId());

        return sensor;
    }

    //Sub-resource for reading part
    @Path("/{id}/readings")
    public SensorReadingResource getSensorReadingResource(@PathParam("id") String id) {
        return new SensorReadingResource(id);
    }
}