/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bean;

import br.com.dao.DAOGeneric;
import br.com.entidades.Pessoa;
import java.io.Serializable;
import java.util.ArrayList;
import static java.util.Comparator.comparing;
import java.util.List;
import java.util.stream.Stream;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;

/**
 *
 * @author 99039833
 */
@ViewScoped
@ManagedBean(name = "pessoaBean")
public class PessoaBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(PessoaBean.class.getName());

    private Pessoa pessoa = new Pessoa();
    private List<Pessoa> pessoas = new ArrayList();
    private StringBuilder sql;
    private String tituloPanel = "Incluir nova Pessoa";
//    private final DAOGeneric<Pessoa> daoPessoa;

    public PessoaBean() {
        carregarListPessoas();
    }

    public void salvar() {
        try {
//            pessoa.setNomeCompleto(pessoa.getNome() + " " + pessoa.getSobrenome());
            pessoa = DAOGeneric.salvarOuAtualizar(pessoa);
            pessoas.stream().filter(pes -> pes.equals(pessoa)).forEach(pes -> pes = pessoa);
            if(Stream.of(pessoas).noneMatch((l) -> l.contains(pessoa))){
                pessoas.add(pessoa);
            }
            pessoas.sort(comparing(Pessoa::getNome));
//            carregarListPessoas();
            tituloPanel = "Incluir nova Pessoa";
            pessoa = new Pessoa();
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo com sucesso", ""));
        } catch (Exception ex) {
            LOG.error(ex.getMessage());
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao Salvar", ""));
        }
    }

    public void editar(Long id) {
        tituloPanel = "Editar dados";   
        pessoa = pessoas.stream().filter(pes -> pes.getId().equals(id)).findAny().get();
    }

    public void excluir(Long id) {
//        try {
            pessoa = new Pessoa(id);
            DAOGeneric.deletar(pessoa);
            pessoas.remove(pessoa);
            pessoa = new Pessoa();
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Excluído com sucesso", ""));
//        } catch (Exception ex) {
//            LOG.error(ex.getMessage(), ex.getCause());
//            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao Excluír", ""));
//        }

    }

    public void cancelar(){
        tituloPanel = "Incluir nova Pessoa";
        pessoa = new Pessoa();
    }
    
    private void carregarListPessoas() {
        sql = new StringBuilder("Select p from br.com.entidades.Pessoa p order by p.nome");
//        pessoas = DAOGeneric.query(sql.toString(), pessoa);
        pessoas =  DAOGeneric.query(new Pessoa());
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    public String getTituloPanel() {
        return tituloPanel;
    }

}
