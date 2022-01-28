package org.stroganov.rs.controller;

import org.stroganov.entities.History;

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
    public Response getAllHistory() {
        List<History> allHistory = libraryDAO.getAllHistory();
        return Response.status(200)
                .entity(allHistory)
                .build();
    }
}
