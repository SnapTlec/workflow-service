package br.com.workflow.dto;

import java.util.Date;

import br.com.workflow.entity.RequestStatus;

public class RequestFilterDTO {
    
    public RequestStatus status;
    public String createdBy;
    public Date createdFrom;
    public Date createdTo;
    public int page;
    public int size;
}
