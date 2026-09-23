package br.com.workflow.rest;

import java.util.List;

import br.com.workflow.dto.Request.Input.RequestCreateDTO;
import br.com.workflow.dto.Request.Output.RequestDTO;
import br.com.workflow.dto.Request.Output.RequestSummaryDTO;
import br.com.workflow.service.Interface.IRequestService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * RequestResource
 */
@Path("/request")
public class RequestResource {

    @Inject 
    private IRequestService requestService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRequests() {
        List<RequestSummaryDTO> requests = requestService.getRequests();
        return Response.ok(requests).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRequestById(@PathParam ("id") Integer id) {
        RequestDTO request = requestService.getRequestById(id);
        return Response.ok(request).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createRequest(RequestCreateDTO dto) {
        RequestDTO createdRequest = requestService.createRequest(dto);
        return Response.status(Response.Status.CREATED).entity(createdRequest).build();
    }
}