/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dao;

import br.com.entidades.Cidade;
import br.com.entidades.Estado;
import br.com.entidades.Pessoa;
import br.com.util.jpaUtil.JPAUtil;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author 99039833
 */
public class DAOGenericTest {
    
    public DAOGenericTest() {
    }

    @Test
    public void testQuery(){
        
        EntityManager em = JPAUtil.getEntityManager();
        List<Pessoa> lista = (List<Pessoa>) em.createQuery("select pes from "+Pessoa.class.getName()+ " pes").getResultList();
        
        lista.forEach(System.out::println);
        
        Assert.assertEquals(4, lista.size());
    }
    
    @Test
    public void testeCidades(){
        Persistivel estado =  new Estado(),
                cidade = new Cidade();
        List<Persistivel> lista = new ArrayList<>();
        lista.addAll(DAOGeneric.query(estado.getClass()));
        lista.addAll(DAOGeneric.query(cidade.getClass()));
        lista.stream().limit(40L).forEach(System.out::println);
        
    }
    
}
