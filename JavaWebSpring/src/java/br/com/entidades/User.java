/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.entidades;

import br.com.dao.Persistivel;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author 99039833
 */
@Entity
@Table(name = "usuario", schema = "modulo3_spring_mvc")
public class User implements Persistivel {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @JoinColumn(name = "id_pessoa", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Pessoa pessoa;
    @Column(name="login", nullable = false, length =  45, unique = true)
    private String login;
    @Column(name="senha", nullable = false, length =  45)
    private String senha;
    @Column(name= "id_tipo_usuario", nullable = false)
    private Long idTipoUsuario;
    private Boolean ativo = Boolean.TRUE;

    //<editor-fold defaultstate="collapsed" desc=">>>>Construtores">
    public User() {
    }
    
    public User(Long id) {
        this.id = id;
    }

    public User(String login, String senha, Pessoa pessoa, Long idTipoUsuario) {
        this.pessoa = pessoa;
        this.login = login;
        this.senha = senha;
        this.idTipoUsuario = idTipoUsuario;
    }
    
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=">>>>Equals e HashCode">
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object other) {
        return other instanceof User && Objects.equals(this.id, ((User) other).id);
    }
    
    @Override
    public String toString() {
        return "\nbr.com.entidades.User[ id=" + id + " ]";
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=">>>>Gets e Sets">
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Pessoa getPessoa() {
        return pessoa;
    }
    
    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Long getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdTipoUsuario(Long idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }
    
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
    
    public Boolean getAtivo() {
        return ativo;
    }
    
    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
    
    @Override
    public Long getIdentifier() {
        return id;
    }
//</editor-fold>
}
