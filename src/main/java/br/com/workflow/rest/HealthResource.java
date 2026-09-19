package br.com.workflow.rest;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path ("health")
public class HealthResource {

    @GET
    public Response healthCheck(){
        return Response.status(Response.Status.OK).build();
    }
}
