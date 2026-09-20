package br.com.workflow.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.workflow.dto.RequestCreateDTO;
import br.com.workflow.dto.RequestFilterDTO;
import br.com.workflow.entity.Message;
import br.com.workflow.entity.MessageType;
import br.com.workflow.entity.Request;
import br.com.workflow.entity.RequestStatus;
import br.com.workflow.entity.ResponseStatus;
import br.com.workflow.entity.ServiceResponse;
import br.com.workflow.service.RequestService;
import br.com.workflow.service.RequestStatusService;

/**
 * RequestResource
 */
@Path("/request")
public class RequestResource {
    @Inject
    private RequestService service;

    private List<Message> notificationMessageList = new ArrayList<Message>();
    private List<Message> errorMessageList = new ArrayList<Message>();

    private int FILTER_SIZE_MAX = 100;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(
        @QueryParam("status") String status,
        @QueryParam("createdBy") String createdBy,
        @QueryParam("createdFrom") Date createdFrom,
        @QueryParam("createdTo") Date createdTo,
        @QueryParam("page")@DefaultValue("0") int page,
        @QueryParam("size")@DefaultValue("20") int size

    ){
        if(createdFrom == null && createdTo != null || createdFrom != null && createdTo == null){
            errorMessageList.add(new Message(MessageType.ERROR, "É necessário informar um intervalo de data."));
        }

        if (status != null && !status.isEmpty()) {
            if (!RequestStatusService.isValid(status)) {
                errorMessageList.add(
                    new Message(MessageType.ERROR, "Status inválido.")
                );
            }
        }

        if(size > FILTER_SIZE_MAX){
            size = 100;
            notificationMessageList.add(
                new Message(
                    MessageType.WARNING, 
                    MessageFormat.format("Quantidade máxima de registros por página ultrapassado. Configurado o parâmetro para o valor máximo: {0}", FILTER_SIZE_MAX)
                )
            );
        }

        if(errorMessageList.size() > 0){
            
            errorMessageList.addAll(notificationMessageList);
            
            return Response.status(Response.Status.BAD_REQUEST)
                            .entity(ServiceResponse.error(errorMessageList))
                            .build();
        }

        RequestFilterDTO requestFilterDTO = new RequestFilterDTO();
        
        if (status != null && !status.isEmpty()) {
            requestFilterDTO.status = RequestStatus.valueOf(status);
        }
        requestFilterDTO.createdBy = createdBy;
        requestFilterDTO.createdFrom = createdFrom;
        requestFilterDTO.createdTo = createdTo;
        requestFilterDTO.page = page;
        requestFilterDTO.size = size;

        try{
            ServiceResponse<List<Request>> serviceResponse = service.getRequests(requestFilterDTO);

            if(serviceResponse.getStatus().equals(ResponseStatus.SUCCESS)){
                
                List<Request> requests = serviceResponse.getData();
                
                    
                if(notificationMessageList.size() > 0){
                    return Response.status(Response.Status.OK)
                            .entity(ServiceResponse.warning(requests, notificationMessageList))
                            .build();
                }

                return Response.status(Response.Status.OK)
                                .entity(ServiceResponse.success(requests, notificationMessageList))
                                .build();

            }

            notificationMessageList.addAll(serviceResponse.getMessages());
            
            return Response.status(Response.Status.BAD_REQUEST)
                            .entity(ServiceResponse.error(notificationMessageList))
                            .build();

        }catch(Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                            .entity(e)
                            .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createtRequest(RequestCreateDTO req){

        try{

            ServiceResponse<Request> serviceResponse = service.createtRequest(req);

            if(ResponseStatus.SUCCESS.equals(serviceResponse.getStatus()))
            {
                URI location = URI.create("request/" + serviceResponse.getData().getId());
                
                return Response.status(Response.Status.CREATED).location(location).entity(serviceResponse.getData()).build();
            }
            
            return Response.status(Response.Status.BAD_REQUEST).entity(serviceResponse).build();

        }catch(Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build();
        }

    }
}