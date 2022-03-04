/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.entidades;

import br.com.dao.Persistivel;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 99039833
 */
@Entity
@Table(name = "estados", schema = "modulo3_spring_mvc")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estado.findAll", query = "SELECT e FROM Estado e")
    , @NamedQuery(name = "Estado.findById", query = "SELECT e FROM Estado e WHERE e.id = :id")
    , @NamedQuery(name = "Estado.findByNome", query = "SELECT e FROM Estado e WHERE e.nome = :nome")})
public class Estado implements Persistivel {

    //<editor-fold defaultstate="collapsed" desc=">>>>Atributos">
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome", nullable = false, length = 45)
    private String nome;
    @Column(name="sigla", nullable = false, length = 2)
    private String sigla;
    @OneToMany(mappedBy = "estado",  cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private List<Cidade> cidades = new ArrayList<>();
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=">>>>Construtores">
    public Estado() {
    }
    
    public Estado(Long id){
        this.id = id;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=">>>>Equal e HashCode">
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.id);
        return hash;
    }
    
    @Override
    public boolean equals(Object other) {
        return other instanceof Estado && Objects.equals(this.id, ((Estado)other).id);
    }
    
    @Override
    public String toString() {
        return "Estado{" + "id=" + id + ", nome=" + nome + ", sigla=" + sigla + '}';
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=">>>>Gets e Sets">
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getSigla() {
        return sigla;
    }
    
    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
    
    public List<Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }
    
    @Override
    public Long getIdentifier() {
        return id;
    }
//</editor-fold>



}
