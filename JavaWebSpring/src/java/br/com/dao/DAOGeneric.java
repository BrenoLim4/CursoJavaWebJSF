/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dao;

import br.com.util.jpaUtil.JPAUtil;
import java.io.Serializable;
import java.util.List;
import static java.util.stream.Collectors.joining;
import java.util.stream.Stream;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author 99039833
 */
public class DAOGeneric implements Serializable {

    private DAOGeneric(){
        
    }
    
    //<editor-fold defaultstate="collapsed" desc=">>>>Salvar ou atualizar">
    public static <E extends Persistivel> E salvarOuAtualizar(E entidade) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction et = em.getTransaction();
        
        et.begin();
        entidade = em.merge(anexar(entidade, em));
        et.commit();
        em.close();
        
        return entidade;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=">>>>Consultas">
    public static <E extends Persistivel> List<E> query(String oql, E entidade) {
        EntityManager em = JPAUtil.getEntityManager();
        return (List<E>) em.createQuery(oql, entidade.getClass()).getResultList();
    }
    
    public static <E extends Persistivel> List<E> query(Class<? extends Persistivel> entidade){
        EntityManager em = JPAUtil.getEntityManager();
        return (List<E>) em.createQuery(montarConsultar(entidade), entidade).getResultList();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=">>>>Deletar">
    public static <E extends Persistivel> void deletar(E... entidades) {        
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        Object[] idsEntidade = getIdsEntidades(entidades);
        String where = montarWhere(idsEntidade);
        em.createQuery("DELETE FROM " + entidades[0].getClass().getName() + where).executeUpdate();
        et.commit();        
        em.close();
    }
    
    private static <E extends Persistivel> Object[] getIdsEntidades(E... entidades){
        int length = entidades.length;
        Object[] ids =  new Object[length];
        for(int i = 0; i < length; i++){
            ids[i] = JPAUtil.getIdEntidade(entidades[i]);
        }
        return ids;
    }
    
    private static String montarWhere(Object... ids){
        return Stream.of(ids)
                .map(ent -> String.valueOf(ent))
                .collect(joining(", ", " WHERE id IN ( ", " )"));        
    } 
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=">>>>Buscar Por Id">
    public static <E extends Persistivel> E buscarPorId(E entidade) {
        EntityManager em = JPAUtil.getEntityManager();
        entidade = em.find((Class<E>) entidade.getClass(), JPAUtil.getIdEntidade(entidade));
        return entidade;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=">>>>Refresh">
    public static <E extends Persistivel> E refresh(E entidade) {
        EntityManager em = JPAUtil.getEntityManager();
        em.refresh(entidade);
        return entidade;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=">>>>Corrigir bug JPA "Entidade desanexada"">
    private static <E extends Persistivel> E anexar(E entidade, EntityManager em) {
        if (em.contains(entidade)) {
            em.detach(entidade);
        }
        return entidade;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=">>>>Montar Query">
    private static String montarConsultar(Class<? extends Persistivel> entidade){
        return "SELECT ent FROM " + entidade.getName()+ " ent";
    }
    //</editor-fold>

}
