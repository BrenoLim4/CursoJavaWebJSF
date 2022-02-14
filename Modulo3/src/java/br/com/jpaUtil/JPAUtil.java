/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author 99039833
 */
@SuppressWarnings("StaticNonFinalUsedInInitialization")
public class JPAUtil {
    
    private static EntityManagerFactory factory = null;
    
    static {
        if (factory == null){
            factory = Persistence.createEntityManagerFactory("modulo3_spring_mvc");
        }
    }
    
    public static EntityManager getEntityManager(){
        return factory.createEntityManager();
    }
}
