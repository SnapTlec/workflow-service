package br.com.workflow.repository.Interface;

import java.util.List;
import java.util.Optional;

import br.com.workflow.entity.Request;

public interface IRequestRepository {
   Request save(Request request);
    Optional<Request> findById(Integer id);
    Optional<Request> findByIdWithDetails(Integer id);
    List<Request> findAll();
    List<Request> findAllWithCreatedBy();


}
