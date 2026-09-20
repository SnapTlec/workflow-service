package br.com.workflow.repository;

import java.util.List;

import br.com.workflow.entity.Request;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;


@ApplicationScoped
public class RequestRepository {
    @PersistenceContext(unitName = "workflow")
    EntityManager em;

    public List<Request> consultarRequestS(){
        String jpql = "SELECT r FROM Request r";
        TypedQuery<Request> result = em.createQuery(jpql, Request.class);

        return result.getResultList();
    }


}
