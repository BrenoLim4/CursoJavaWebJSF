/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dao;

import br.com.jpaUtil.JPAUtil;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author 99039833
 * @param <E>
 */
public class DAOGeneric<E> {
    
    public void salvar(E entidade){
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction et = em.getTransaction();
        
        et.begin();
        em.persist(entidade);
        et.commit();
        
        em.close();
    }
    
    public void deletar(E entidade){
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction et = em.getTransaction();
        
        et.begin();
        em.remove(entidade);
        et.commit();
        
        em.close();
    }
    
    
}
