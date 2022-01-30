package org.stroganov.rs.controller;

import org.stroganov.entities.History;
import org.stroganov.rs.filter.AdminStatusNeeded;
import org.stroganov.rs.filter.JWTTokenNeeded;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/api/history")
public class HistoryController extends Controller {


    @POST
    @Path("/")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @JWTTokenNeeded
    public Response addHistoryEvent(History history) {
        boolean operationResult = libraryDAO.addHistoryEvent(history);
        return Response.status(200)
                .entity(operationResult)
                .build();
    }

    @GET
    @Path("/")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @JWTTokenNeeded
    @AdminStatusNeeded
    public Response getAllHistory() {
        List<History> allHistory = libraryDAO.getAllHistory();
        return Response.status(200)
                .entity(allHistory)
                .build();
    }
}
