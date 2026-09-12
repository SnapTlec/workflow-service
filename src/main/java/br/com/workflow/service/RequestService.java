package br.com.workflow.service;

import java.util.ArrayList;
import java.util.List;

import javax.imageio.spi.ServiceRegistry;

import br.com.workflow.dto.RequestDTO;
import br.com.workflow.entity.Request;
import br.com.workflow.entity.RequestStatus;
import br.com.workflow.entity.ServiceResponse;
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

    public ServiceResponse<Request> createtRequest(RequestDTO request){

        try{

            Request request1 = mapper.toEntity(request);
            
            request1.id = requests.size() + 1;
    
            request1.status = RequestStatus.CREATED;
    
            if(requests.add(request1)){
                return ServiceResponse.success(request1);
            }

            return ServiceResponse.error("Não foi possível criar o chamado.");

        }catch(Exception e){
            return ServiceResponse.error(e.toString());
        }

    }
}
