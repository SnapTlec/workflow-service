package br.com.workflow.repository;


import java.util.List;
import br.com.workflow.dto.RequestFilterDTO;
import br.com.workflow.entity.Request;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;


@ApplicationScoped
public class RequestRepository {
    @PersistenceContext(unitName = "workflow")
    EntityManager em;

    public List<Request> consultarRequestS(RequestFilterDTO filter){

        StringBuilder jpql = new StringBuilder("SELECT r FROM Request r WHERE 1=1");
        
        if(filter.status != null)
            {
                jpql.append(" AND r.status = :status");
            }
            
        if(filter.createdBy != null){
            jpql.append(" AND r.createdBy = :createdby");

        }
        
        if (filter.createdFrom != null && filter.createdTo != null) {
            jpql.append(" AND r.createdAt BETWEEN :createdFrom AND :createdTo");
        }
            
        jpql.append(" ORDER BY r.createdAt DESC");
        
        TypedQuery<Request> query = em.createQuery(jpql.toString(), Request.class);

        if(filter.status != null)
        {
            query.setParameter("status", filter.status);
        }
            
        if(filter.createdBy != null){
            query.setParameter("createdby", filter.createdBy);
        }
        
        if (filter.createdFrom != null && filter.createdTo != null) {
            query.setParameter("createdFrom", filter.createdFrom);
            query.setParameter("createdTo", filter.createdTo);
        }
        
        query.setFirstResult(filter.page*filter.size).setMaxResults(filter.size);
        
        
        return query.getResultList();
        }
        
        
}
