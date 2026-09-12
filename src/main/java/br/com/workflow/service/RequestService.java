package br.com.workflow.service;

import java.util.ArrayList;
import java.util.List;

import br.com.workflow.dto.RequestDTO;
import br.com.workflow.entity.Request;
import br.com.workflow.entity.RequestStatus;
import br.com.workflow.mapper.RequestMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class RequestService {

    private List<Request> requests = new ArrayList<>();
    
    @Inject 
    private RequestMapper mapper;

    public List<Request> getRequests(){
        return requests;
    }

    public Boolean createtRequest(RequestDTO request){

        Request request1 = mapper.toEntity(request);
        
        request1.id = requests.size() + 1;

        request1.status = RequestStatus.CREATED;

        requests.add(request1);

        return true;
    }
}
