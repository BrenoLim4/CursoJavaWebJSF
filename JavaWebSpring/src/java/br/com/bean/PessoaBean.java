/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bean;

import br.com.dao.DAOGeneric;
import br.com.entidades.Pessoa;
import br.com.entidades.Sexo;
import br.com.entidades.User;
import br.com.security.AutenticarImpl;
import java.io.Serializable;
import java.util.ArrayList;
import static java.util.Comparator.comparing;
import java.util.List;
import static java.util.stream.Collectors.toList;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.apache.log4j.Logger;

/**
 *
 * @author 99039833
 */
@ViewScoped
@ManagedBean(name = "pessoaBean")
public class PessoaBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(PessoaBean.class);

    private Pessoa pessoa = new Pessoa();
    private List<Pessoa> pessoas = new ArrayList();
    private StringBuilder sql;
    private String tituloPanel = "Incluir novo Usuário";
    private Boolean selecionarTodos = Boolean.FALSE;
    private final List<SelectItem> listSexo = new ArrayList<>();
    private Sexo tipoSexo;
    private String login;
    private String senha;
    private Long tipoUsuario;
    private User users = new User();

    public PessoaBean() {
    }

    @PostConstruct
    private void init() {
        carregarListPessoas();
        carregarListSexo();
    }

    private void carregarListPessoas() {
        pessoas = DAOGeneric.query(Pessoa.class);
        pessoas.sort(comparing(Pessoa::getNome));
    }

    private void carregarListSexo() {
        List<Sexo> sexos = DAOGeneric.query(Sexo.class);
        sexos.forEach(sexo -> {
            listSexo.add(new SelectItem(sexo, sexo.getDescricao(), String.valueOf(sexo.getId())));
        });
    }

    public void limparCampos() {
        pessoa = new Pessoa();
        tipoSexo = new Sexo();
        login = null;
        senha = null;
        tipoUsuario = null;
    }

    public void salvar() {
        try {
            pessoa.setSexo(tipoSexo);
            pessoa = DAOGeneric.salvarOuAtualizar(pessoa);

            if (!pessoas.contains(pessoa)) {
                pessoas.add(pessoa);
                AutenticarImpl.getInstance().incluirUsuario(new User(login, senha, pessoa, tipoUsuario));
            } else {
                pessoas.stream()
                        .filter(pes -> pes.equals(pessoa))
                        .forEach(pes -> pes = pessoa);
            }
            pessoas.sort(comparing(Pessoa::getNome));
            tituloPanel = "Incluir novo Usuário";
            limparCampos();
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo com sucesso", ""));
        } catch (Exception ex) {
            System.err.println("ERROR" + ex.toString());
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao Salvar", ""));
        }
    }

    public void excluir() {
        try {

            DAOGeneric.deletar(pessoa);
            pessoas.remove(pessoa);
            limparCampos();
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Excluído com sucesso", ""));
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex.getCause());
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao Excluír", ""));
        }
    }

    public void ajustarSelecionarTodos(boolean selecionar) {
        pessoas.stream().forEach(pes -> pes.setSelecione(selecionar));
    }

    public void excluirSelecionados() {
        try {
            List<Pessoa> deletePessoas = pessoas.stream().filter(pes -> pes.getSelecione()).collect(toList());
            if (!deletePessoas.isEmpty()) {
                DAOGeneric.deletar(deletePessoas.toArray(new Pessoa[0]));
                pessoas.removeAll(deletePessoas);
                FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados excluídos com sucesso", ""));
                return;
            }
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Selecione ao menos uma pessoa.", ""));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao Excluír dados", ""));
        }
    }

    //<editor-fold defaultstate="collapsed" desc=">>>>Gets e Sets">
    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public User getUsers() {
        return users;
    }

//    public void setUsers(Users users) {
//        this.users = users;
//    }
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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

    public void setTituloPanel(String tituloPanel) {
        this.tituloPanel = tituloPanel;
    }

    public Boolean getSelecionarTodos() {
        return selecionarTodos;
    }

    public void setSelecionarTodos(Boolean selecionarTodos) {
        this.selecionarTodos = selecionarTodos;
    }

    public List<SelectItem> getListSexo() {
        return listSexo;
    }

    public Sexo getTipoSexo() {
        return tipoSexo;
    }

    public void setTipoSexo(Sexo tipoSexo) {
        this.tipoSexo = tipoSexo;
    }
    
    public Long getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(Long tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    

//</editor-fold>

}
