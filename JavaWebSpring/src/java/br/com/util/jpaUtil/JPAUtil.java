/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.util.jpaUtil;

import br.com.dao.Persistivel;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
//import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author 99039833
 */
public class JPAUtil {
    
    private static EntityManagerFactory factory;
    
    static {
        if (factory == null){
            factory = Persistence.createEntityManagerFactory("JavaWebSpringPU");
        }
    }
    
    public static EntityManager getEntityManager(){
        return factory.createEntityManager();
    }
    
    public static <E extends Persistivel> Object getIdEntidade(E entidade){
        return factory.getPersistenceUnitUtil().getIdentifier(entidade);
        
    } 
}
