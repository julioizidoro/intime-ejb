package br.com.intime.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public abstract class AbstractRepository<T> {

    @PersistenceContext
    private EntityManager em;
    protected final Class clazz;

    
    public AbstractRepository(Class clazz) {
        this.clazz = clazz;
    }

    public T create(T entity) {
        em.persist(entity);
        return entity;
    }

    public T update(T entity) {
        return em.merge(entity);
    }

    public void remove(Integer id) {
        em.remove(em.getReference(clazz, id));
    }

    public T find(Integer id) {
        return (T) em.find(clazz, id);
    }
    
    public List<T> list(String sql) {
        List<T> list = em.createQuery(sql).getResultList();
        if (list==null){
            list = new ArrayList<>();
        }
        return list;
    }

    public List<T> list(String sql, LocalDate dataInicial, LocalDate dataFinal) {
        Query q = em.createQuery(sql);
        if (dataInicial!=null){
            q.setParameter("dataInicial", dataInicial);
        }
        if (dataFinal!=null){
            q.setParameter("dataFinal", dataFinal);
        }
        List<T> list = q.getResultList();
        if (list==null){
            list = new ArrayList<>();
        }
        return list;
    }
    
    public T find(String sql) {
        Query q = em.createQuery(sql);
         T t = null;
         if (q != null) {
            if (q.getResultList().size()>0){
                t = (T) q.getSingleResult();
            }
        }
        return (T) t;
    }

}
