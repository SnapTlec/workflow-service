package br.com.workflow.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.workflow.dto.Request.Output.RequestDTO;
import br.com.workflow.dto.Request.Output.RequestSummaryDTO;
import br.com.workflow.dto.User.UserDTO;
import br.com.workflow.entity.Request;
import br.com.workflow.entity.User;

@Mapper(componentModel = "cdi")
public interface RequestMapper {

    RequestDTO toDto(Request request);

    List<RequestSummaryDTO> toSummaryDtoList(List<Request> requests);

    @Mapping(source = "createdBy.login", target = "createdBy")
    RequestSummaryDTO toSummaryDto(Request request);


    UserDTO toUserDto(User user);
}
