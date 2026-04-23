package resource;

import model.Sensor;
import model.SensorReading;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.*;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SensorReadingResource {

    private static Map<String, List<SensorReading>> sensorReadingsMap = new HashMap<>();
    private String sensorId;

    public SensorReadingResource(String sensorId) {
        this.sensorId = sensorId;
    }

    //GET readings for a sensor
    @GET
    public List<SensorReading> getReadings() {
        return sensorReadingsMap.getOrDefault(sensorId, new ArrayList<>());
    }

    //POST a new reading for the sensor
    @POST
    public Object addReading(SensorReading reading) {

        Sensor sensor = SensorResource.sensorsMap.get(sensorId);

        if (sensor == null) {
            throw new exception.LinkedResourceNotFoundException("Sensor not found");
        }
        
        if (sensor.getStatus().equals("MAINTENANCE") || sensor.getStatus().equals("OFFLINE")) {
            throw new exception.SensorUnavailableException("The sensor is either maintenance or offline");
        }
        
        //Updating the vallue
        sensor.setCurrentValue(reading.getValue());

        sensorReadingsMap.putIfAbsent(sensorId, new ArrayList<>());
        sensorReadingsMap.get(sensorId).add(reading);

        return reading;
    }
}