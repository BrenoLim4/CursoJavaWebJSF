/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bean;

import br.com.dao.DAOGeneric;
import br.com.entidades.Endereco;
import br.com.entidades.Pessoa;
import br.com.entidades.Sexo;
import br.com.entidades.User;
import br.com.security.AutenticarImpl;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import static java.util.Comparator.comparing;
import java.util.List;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

/**
 *
 * @author 99039833
 */
@ViewScoped
@ManagedBean(name = "pessoaBean")
public class PessoaBean implements Serializable {

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
//    private User users = new User();
    private Endereco endereco = new Endereco();

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
        endereco = new Endereco();
        tipoSexo = new Sexo();
        login = null;
        senha = null;
        tipoUsuario = null;
        tituloPanel = "Incluir novo Usuário";
        
    }

    public void salvar() {
        try {
            pessoa.setSexo(tipoSexo);
            pessoa = DAOGeneric.salvarOuAtualizar(pessoa);
            
            endereco.setIdPessoa(pessoa.getId());
            endereco = DAOGeneric.salvarOuAtualizar(endereco);
            
//            pessoa.setEnderecoAtual(endereco);
            

            if (!pessoas.contains(pessoa)) {
                pessoas.add(pessoa);
                AutenticarImpl.getInstance().incluirUsuario(new User(login, senha, pessoa, tipoUsuario));
            } else {
                pessoas.stream()
                        .filter(pes -> pes.equals(pessoa))
                        .forEach(pes -> pes = pessoa);
            }
            pessoas.sort(comparing(Pessoa::getNome));
            limparCampos();
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo com sucesso", ""));
        } catch (Exception ex) {
            ex.printStackTrace();
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

    public void pesquisaCep(AjaxBehaviorEvent event) {
        try{
            URL url = new URL("https://viacep.com.br/ws/"+endereco.getCep()+"/json/");
            InputStream is = url.openConnection().getInputStream();            
            endereco =  new Gson().fromJson(new InputStreamReader(is, "UTF-8"), Endereco.class);
        } catch(JsonIOException | JsonSyntaxException | IOException ex){
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "CEP inválido", ""));    
            ex.printStackTrace();
        }
    }

    //<editor-fold defaultstate="collapsed" desc=">>>>Gets e Sets">
    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }


    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
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
