/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bean;

import br.com.dao.DAOGeneric;
import br.com.entidades.Endereco;
import br.com.entidades.Pessoa;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author 99039833
 */
public class PessoaBeanTest {

    public PessoaBeanTest() {
    }
    
    @Test
    public void testeNamedQuery(){
        int all, countId, name;
        all = DAOGeneric.getEntityManager().createNamedQuery("Pessoa.findAll").getFirstResult();
        countId = DAOGeneric.getEntityManager().createNamedQuery("Pessoa.findById").setParameter("id", 1l).getFirstResult();
        name = DAOGeneric.getEntityManager().createNamedQuery("Pessoa.findByNome").setParameter("nome", "Breno").getFirstResult();
        
        Assert.assertEquals(4, all);
        Assert.assertEquals(1, countId);
        Assert.assertEquals(2, name);
    }

    @Test
    public void testListaPorParametros() {
        Pessoa pessoa = (Pessoa) DAOGeneric.getEntityManager()
                .createQuery(" from Pessoa where nome = :nome")
                .setParameter("nome", "Breno").getSingleResult();
        Assert.assertNotNull("Valor Nulo", pessoa);
        
        System.out.println(pessoa.toString());
    }
    
    @Test
    public void testCount(){
        long qtdPessoaMasculinas = (long) DAOGeneric.getEntityManager()
                .createQuery("select count(p.id) from Pessoa p where p.sexo.id = :idSexo")
                .setParameter("idSexo", 2l)
                .getSingleResult();
        
        Assert.assertEquals(1l, qtdPessoaMasculinas);
    }

    @Test
    public void testPesquisaCep() throws MalformedURLException, UnsupportedEncodingException, IOException {
        String cep = "60736140";
        Endereco endereco = new Endereco();
        endereco.setCep(cep);

        URL url = new URL("https://viacep.com.br/ws/" + endereco.getCep() + "/json/");
        InputStream is = url.openConnection().getInputStream();
        endereco = new Gson().fromJson(new InputStreamReader(is, "UTF-8"), Endereco.class);
//        System.out.println("cep: " + endereco.getCep() + "\nRua: " + endereco.getLogradouro() + "\nBairro: " + endereco.getBairro());

        Assert.assertNotNull(endereco.getCep());
        Assert.assertNotNull(endereco.getLogradouro());
        Assert.assertNotNull(endereco.getBairro());
        
    }

}
