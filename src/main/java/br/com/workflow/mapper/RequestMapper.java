package br.com.workflow.mapper;

import br.com.workflow.dto.RequestDTO;
import br.com.workflow.entity.Request;

import org.mapstruct.Mapper;

@Mapper
public interface RequestMapper {
    Request toEntity(RequestDTO request);
    RequestDTO toDTO(Request request);
}
