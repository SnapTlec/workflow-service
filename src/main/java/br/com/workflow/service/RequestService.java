package br.com.workflow.service;

import java.util.Collections;
import java.util.List;

import br.com.workflow.dto.Request.Input.RequestCreateDTO;
import br.com.workflow.dto.Request.Output.RequestDTO;
import br.com.workflow.dto.Request.Output.RequestSummaryDTO;
import br.com.workflow.entity.Request;
import br.com.workflow.entity.RequestStatus;
import br.com.workflow.entity.User;
import br.com.workflow.mapper.RequestMapper;
import br.com.workflow.repository.Interface.IRequestRepository;
import br.com.workflow.repository.Interface.IUserRepository;
import br.com.workflow.service.Interface.IRequestService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class RequestService implements IRequestService {
    
    @Inject 
    private IRequestRepository requestRepository;

    @Inject 
    private IUserRepository userRepository;

    @Inject 
    private RequestMapper requestMapper;

    @Override
    @Transactional
    public List<RequestSummaryDTO> getRequests() {
        List<Request> requests = requestRepository.findAll();
        return requestMapper.toSummaryDtoList(requests);
    }

    @Override
    @Transactional
    public RequestDTO getRequestById(Integer id) {
        Request request = requestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chamado não encontrado: " + id));
        return requestMapper.toDto(request);
    }

    @Override
    @Transactional
    public RequestDTO createRequest(RequestCreateDTO dto) {
        User creator = userRepository.findByLogin(dto.getCreatedBy())
                .orElseThrow(() -> new RuntimeException("Usuário criador não encontrado: " + dto.getCreatedBy()));

        List<User> additionalRequesters = Collections.emptyList();
        if (dto.getAdditionalRequesterIds() != null && !dto.getAdditionalRequesterIds().isEmpty()) {
            additionalRequesters = userRepository.findAllById(dto.getAdditionalRequesterIds());
        }

        Request request = Request.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .status(RequestStatus.CREATED)
                .createdBy(creator)
                .additionalRequesters(additionalRequesters)
                .build();

        Request savedRequest = requestRepository.save(request);
        return requestMapper.toDto(savedRequest);
    }
}
