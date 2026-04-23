package exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class SensorUnavailableMapper implements ExceptionMapper<SensorUnavailableException> {

    @Override
    public Response toResponse(SensorUnavailableException ex) {
        //403
        return Response.status(Response.Status.FORBIDDEN)
                .entity("{\"Error Occurred\": \"" + ex.getMessage() + "\"}")
                .build();
    }
}