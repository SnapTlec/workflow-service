package br.com.workflow.service;

import java.util.ArrayList;
import java.util.List;

import br.com.workflow.dto.RequestDTO;
import br.com.workflow.dto.RequestFilterDTO;
import br.com.workflow.entity.Message;
import br.com.workflow.entity.MessageType;
import br.com.workflow.entity.Request;
import br.com.workflow.entity.RequestStatus;
import br.com.workflow.entity.ServiceResponse;
import br.com.workflow.mapper.RequestMapper;
import br.com.workflow.repository.RequestRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class RequestService {
    
    @Inject 
    private RequestMapper mapper;

    @Inject
    private  RequestRepository requestRepository;

    public ServiceResponse<List<Request>> getRequests(RequestFilterDTO requestFilter){
        
        if(requestFilter.createdFrom != null && requestFilter.createdTo != null && requestFilter.createdFrom.after(requestFilter.createdTo))
        {
            return ServiceResponse.error(new Message(MessageType.ERROR, "Data inicial não poder ser maior do que a data final"));
        }

        List<Request> requests = requestRepository.consultarRequestS();

        return ServiceResponse.success(requests);
    }

    public ServiceResponse<Request> createtRequest(RequestDTO request){

        try{
            return ServiceResponse.error(new Message(MessageType.ERROR, "Não foi possível criar o chamado."));

        }catch(Exception e){
            return ServiceResponse.error(new Message(MessageType.ERROR, e.toString()));
        }

    }
}
