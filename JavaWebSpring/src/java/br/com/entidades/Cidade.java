/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.entidades;

import br.com.dao.Persistivel;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author 99039833
 */
@Entity
@Table(name = "cidade", schema = "modulo3_spring_mvc")
@NamedQueries({
    @NamedQuery(name = "Cidade.findAll", query = "SELECT c FROM Cidade c")
    , @NamedQuery(name = "Cidade.findById", query = "SELECT c FROM Cidade c WHERE c.id = :id")
    , @NamedQuery(name = "Cidade.findByNome", query = "SELECT c FROM Cidade c WHERE c.nome = :nome")})
public class Cidade implements Persistivel {

    //<editor-fold defaultstate="collapsed" desc=">>>>Atributos">
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;
    @JoinColumn(name = "estado_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false)
    private Estado estado;
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=">>>>Contrutores">
    public Cidade() {
    }
    
    public Cidade(Long id) {
        this.id = id;
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
        return other instanceof Cidade && Objects.equals(this.id, ((Cidade) other).id);
    }

    @Override
    public String toString() {
        return "Cidade{" + "id=" + id + ", nome=" + nome + ", estado=" + estado + '}';
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
    
    public Estado getEstado() {
        return estado;
    }
    
    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
    
    
    @Override
    public Long getIdentifier() {
        return id;
    }
//</editor-fold>
    
}
