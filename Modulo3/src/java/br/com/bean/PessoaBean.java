/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bean;

import br.com.dao.DAOGeneric;
import br.com.entidades.Pessoa;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author 99039833
 */
@SessionScoped
@ManagedBean(name = "pessoaBean")
public class PessoaBean implements Serializable{
    
    private Pessoa pessoa = new Pessoa();
    private final DAOGeneric<Pessoa> daoPessoa;

    public PessoaBean() {
        daoPessoa = new DAOGeneric<>();
    }
    
    public String salvar(){
        try{
            pessoa.setNomeCompleto(pessoa.getNome() + " " + pessoa.getSobrenome());
            daoPessoa.salvar(pessoa);
        }catch(Exception ex){
            
        }
        return "";
    }

    
    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    
}
