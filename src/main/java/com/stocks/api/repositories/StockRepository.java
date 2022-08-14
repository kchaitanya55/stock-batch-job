package com.stocks.api.repositories;

import com.stocks.api.model.StockDaily;
import org.hibernate.JDBCException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StockRepository  {
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    public boolean saveAll(List<StockDaily> stocks){
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            stocks.forEach(stockDaily -> entityManager.merge(stockDaily));
            entityManager.getTransaction().commit();
            return true;
        }catch (JDBCException exception){
            return false;
        }finally {
            entityManager.clear();
            entityManager.close();
        }
    }

    public boolean deleteAll(){

        EntityManager entityManager=entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.createNativeQuery("DELETE  FROM STOCKS").executeUpdate();
            entityManager.getTransaction().commit();
            return true;
        }catch (JDBCException exception){
            return false;
        }finally {
            entityManager.clear();
            entityManager.close();
        }
    }

    public List<StockDaily> findAll(){
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        try{
            return entityManager.createQuery("Select t from StockDaily t order by stockDate desc").getResultList();

        }catch (JDBCException exception){
            return new ArrayList<>();
        }finally {
            entityManager.clear();
            entityManager.close();
        }
    }



}
