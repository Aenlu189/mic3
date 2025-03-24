package mic3;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/architects")
public class ServiceEndpoints {
    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(RegisterRequest c) {
        Mic3Service s = new Mic3Service();
        return s.register(c);
    }

    @POST
    @Path("/assign")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response assign(AssignRequest c) {
        System.out.println("Architects Register called with: " + c);
        Mic3Service s = new Mic3Service();
        return s.assign(c);
    }

    @POST
    @Path("/check")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response check(CheckRequest c) {
        Mic3Service s = new Mic3Service();
        return s.check(c);
    }
}

