package exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RoomNotEmptyMapper implements ExceptionMapper<RoomNotEmptyException> {

    @Override
    public Response toResponse(RoomNotEmptyException ex) {
        //409
        return Response.status(Response.Status.CONFLICT)
                .entity("{\"Error Occurred\": \"" + ex.getMessage() + "\"}")
                .build();
    }
}