package br.com.workflow.repository;

import java.util.List;
import java.util.Optional;

import br.com.workflow.entity.Request;
import br.com.workflow.repository.Interface.IRequestRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class RequestRepository implements IRequestRepository {
    
    @PersistenceContext(unitName = "workflow")
    private EntityManager entityManager;

    @Override
    public Request save(Request request) {
        if (request.getId() == null) {
            entityManager.persist(request);
            return request;
        } else {
            return entityManager.merge(request);
        }
    }

    @Override
    public Optional<Request> findById(Integer id) {
        Request request = entityManager.find(Request.class, id);
        return Optional.ofNullable(request);
    }

    @Override
    public Optional<Request> findByIdWithDetails(Integer id) {
        List<Request> results = entityManager.createQuery(
                "SELECT DISTINCT r FROM Request r " +
                "JOIN FETCH r.createdBy " +
                "LEFT JOIN FETCH r.additionalRequesters " +
                "WHERE r.id = :id", Request.class)
                .setParameter("id", id)
                .getResultList();

        return results.stream().findFirst();
    }

    @Override
    public List<Request> findAllWithCreatedBy() {
        return entityManager.createQuery(
                "SELECT r FROM Request r " +
                "JOIN FETCH r.createdBy " +
                "ORDER BY r.createdAt DESC", Request.class)
                .getResultList();
    }

    @Override
    public List<Request> findAll() {
        return entityManager.createQuery(
            "SELECT r FROM Request r ", Request.class
        ).getResultList();
    }
       
}
