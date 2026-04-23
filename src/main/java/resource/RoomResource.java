package resource;

import model.Room;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;
import java.util.logging.Logger;

@Path("/rooms")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RoomResource {

    public static Map<String, Room> roomMap = new HashMap<>();
    private static final Logger logger = Logger.getLogger(RoomResource.class.getName());

    //GET all the rooms
    @GET
    public Collection<Room> getAllRooms() {
        return roomMap.values();
    }

    //GET a specific room by ID
    @GET
    @Path("/{id}")
    public Response getRoomById(@PathParam("id") String id) {
        Room room = roomMap.get(id);
        if (room == null) {
            return Response.status(404)
                .entity("{\"error\": \"Room not found\"}")
                .build();
        }
        return Response.ok(room).build();
    }

    //POST adding a room
    @POST
    public Response addingRooms(Room room) {
        logger.info("Creating a Room: " + room.getId());
        roomMap.put(room.getId(), room);
        return Response.status(201).entity(room).build();
    }

    //DELETE Deleting a room
    @DELETE
    @Path("/{id}")
    public String deleteRoom(@PathParam("id") String id) {

        Room room = roomMap.get(id);

        if (room == null) {
            throw new RuntimeException("Room is not registered");
        }

        if (!room.getSensorIds().isEmpty()) {
            logger.warning("Trying to delete a room (With sensors): " + id);
            throw new exception.RoomNotEmptyException("The room cannot be deleted because it has sensors");
        }

        logger.info("Deleting room: " + id);

        roomMap.remove(id);
        return "{\"message\": \"Room deleted\"}";
    }
}
