package br.com.workflow.service.Interface;

import java.util.List;

import br.com.workflow.dto.Request.Input.RequestCreateDTO;
import br.com.workflow.dto.Request.Output.RequestDTO;
import br.com.workflow.dto.Request.Output.RequestSummaryDTO;

public interface IRequestService {
    List<RequestSummaryDTO> getRequests();
    RequestDTO getRequestById(Integer id);
    RequestDTO createRequest(RequestCreateDTO dto);
}
