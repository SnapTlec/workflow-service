package br.com.workflow.mapper;

import br.com.workflow.dto.RequestCreateDTO;
import br.com.workflow.entity.Request;

import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface RequestMapper {
    Request toEntity(RequestCreateDTO request);
    RequestCreateDTO toDTO(Request request);
}

