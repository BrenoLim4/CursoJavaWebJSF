/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dao;

import br.com.jpaUtil.JPAUtil;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Stream;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author 99039833
 */
public class DAOGeneric implements Serializable {

    public static <E extends Persistivel> E salvarOuAtualizar(E entidade) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction et = em.getTransaction();

        et.begin();        
        entidade = em.merge(anexar(entidade, em));        
        et.commit();
        em.close();
        
        return entidade;
    }

    public static <E extends Persistivel> List<E> query(String oql, E entidade) {
        EntityManager em = JPAUtil.getEntityManager();
        return (List<E>) em.createQuery(oql, entidade.getClass()).getResultList();
    }
    
    public static <E extends Persistivel> List<E> query(E entidade){
        EntityManager em = JPAUtil.getEntityManager();
        return (List<E>) em.createQuery(montarConsultar(entidade.getClass()), entidade.getClass()).getResultList();
    }

    public static <E extends Persistivel> void deletar(E entidade) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.remove(em.contains(entidade) ? entidade : em.merge(entidade));
        et.commit();

        em.close();
    }

    public static <E extends Persistivel> E buscarPorId(E entidade, Long primaryKey) {
        EntityManager em = JPAUtil.getEntityManager();
        entidade = em.find((Class<E>) entidade.getClass(), primaryKey);
        return entidade;
    }

    public static <E extends Persistivel> E refresh(E entidade) {
        EntityManager em = JPAUtil.getEntityManager();
        em.refresh(entidade);
        return entidade;
    }

    private static <E extends Persistivel> E anexar(E entidade, EntityManager em) {
        if (em.contains(entidade)) {
            em.detach(entidade);
        }
        return entidade;
    }

    public static DAOGeneric getInstace() {
        return new DAOGeneric();
    }
    
    private static String montarConsultar(Class<? extends Persistivel> entidade){
        return "SELECT ent FROM " + entidade.getCanonicalName() + " ent";
    }

}
