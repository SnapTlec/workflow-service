package br.com.workflow.dto.Request.Output;

import java.time.LocalDateTime;

import br.com.workflow.entity.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
@AllArgsConstructor
public class RequestSummaryDTO{
    private  Integer id;
    private  String title;
    private  RequestStatus status;
    private  LocalDateTime createdAt;
    private  String createdBy;
}