package br.com.workflow.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import br.com.workflow.dto.RequestDTO;
import br.com.workflow.service.RequestService;

/**
 * RequestResource
 */
@Path("/request")
public class RequestResource {
    @Inject
    private RequestService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(){
        return Response.ok(service.getRequests()).build();
    }

    @POST 
    public Response createtRequest(RequestDTO req){

        if(service.createtRequest(req))
        {
            return Response.status(Response.Status.CREATED).build();
        }

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();

    }
}