package br.com.workflow.dto.Request.Output;

import java.time.LocalDateTime;
import java.util.List;

import br.com.workflow.dto.User.UserDTO;
import br.com.workflow.entity.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor 
public class RequestDTO {
    private  Integer id;
    private  String title;
    private  String description;
    private  RequestStatus status;
    private  UserDTO createdBy;
    private  LocalDateTime createdAt;
    private  List<UserDTO> additionalRequesters;
}
