/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.entidades;

import br.com.dao.Persistivel;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;

/**
 *
 * @author 99039833
 */
@Entity
@Table(name="endereco", schema = "modulo3_spring_mvc")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Endereco.findAll", query = "SELECT e FROM Endereco e")
    , @NamedQuery(name = "Endereco.findById", query = "SELECT e FROM Endereco e WHERE e.id = :id")
    , @NamedQuery(name = "Endereco.findByIdPessoa", query = "SELECT e FROM Endereco e WHERE e.idPessoa = :idPessoa")
    , @NamedQuery(name = "Endereco.findByCep", query = "SELECT e FROM Endereco e WHERE e.cep = :cep")
    , @NamedQuery(name = "Endereco.findByLogradouro", query = "SELECT e FROM Endereco e WHERE e.logradouro = :logradouro")
    , @NamedQuery(name = "Endereco.findByBairro", query = "SELECT e FROM Endereco e WHERE e.bairro = :bairro")
    , @NamedQuery(name = "Endereco.findByLocalidade", query = "SELECT e FROM Endereco e WHERE e.localidade = :localidade")
    , @NamedQuery(name = "Endereco.findByUf", query = "SELECT e FROM Endereco e WHERE e.uf = :uf")})
public class Endereco implements Persistivel {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long id;
    @Basic(optional = false)
    @Column(name = "id_pessoa", nullable = false)
    private Long idPessoa;
    @Basic(optional = false)
    @Required(message = "Campo [CEP] é obrigatório")
    @Column(nullable = false, length = 8)
    private String cep;
    @Basic(optional = false)
    @Column(nullable = false, length = 100)
    private String logradouro;
    @Basic(optional = false)
    @Column(nullable = false, length = 30)
    private String bairro;
    @Basic(optional = false)
    @Column(nullable = false, length = 30)
    private String localidade;
    @Basic(optional = false)
    @Column(nullable = false, length = 2)
    private String uf;

    public Endereco() {
    }

    public Endereco(Long id) {
        this.id = id;
    }    

    //<editor-fold defaultstate="collapsed" desc=">>>>Equals e HashCode">
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object other) {
        return other instanceof Endereco && Objects.equals(id, ((Endereco) other).id);
    }
    
    @Override
    public String toString() {
        return "br.com.entidades.Endereco[ id=" + id + " ]";
    }
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=">>>>Gets e Sets">
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getIdPessoa() {
        return idPessoa;
    }
    
    public void setIdPessoa(Long idPessoa) {
        this.idPessoa = idPessoa;
    }
    
    public String getCep() {
        return cep;
    }
    
    public void setCep(String cep) {
        this.cep = cep;
    }
    
    public String getLogradouro() {
        return logradouro;
    }
    
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }
    
    public String getBairro() {
        return bairro;
    }
    
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    
    public String getLocalidade() {
        return localidade;
    }
    
    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }
    
    public String getUf() {
        return uf;
    }
    
    public void setUf(String uf) {
        this.uf = uf;
    }
    
    @Override
    public Long getIdentifier() {
        return id;
    }
//</editor-fold>

    
}
