package org.stroganov.rs.controller;

import org.stroganov.entities.History;
import org.stroganov.models.HistoryDTO;
import org.stroganov.rs.filter.AdminStatusNeeded;
import org.stroganov.rs.filter.JWTTokenNeeded;
import org.stroganov.util.TransitionObjectsService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/api")
public class HistoryController extends Controller {


    @POST
    @Path("/history")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @JWTTokenNeeded
    public Response addHistoryEvent(HistoryDTO historyDTO) {
        History history = TransitionObjectsService.getHistory(historyDTO);
        boolean operationResult = libraryDAO.addHistoryEvent(history);
        return Response.status(Response.Status.OK)
                .entity(operationResult)
                .build();
    }

    @GET
    @Path("/history")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @JWTTokenNeeded
    @AdminStatusNeeded
    public Response getAllHistory() {
        List<History> allHistory = libraryDAO.getAllHistory();
        List<HistoryDTO> historyDTOList = TransitionObjectsService.getHistoryDTOList(allHistory);
        return Response.status(Response.Status.OK)
                .entity(historyDTOList)
                .build();
    }
}
