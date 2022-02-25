/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.entidades;

import br.com.dao.Persistivel;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author 99039833
 */
@Entity
@Table(name = "sexo", schema = "modulo3_spring_mvc")
public class Sexo implements Persistivel {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "descricao", nullable = false)
    private String descricao;
    @Transient
    @OneToMany(mappedBy = "id_sexo", fetch = FetchType.LAZY)
    private List<Pessoa> pessoas;
    
    @Override
    public Long getIdentifier() {
        return id;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Sexo && Objects.equals(this.id, ((Sexo) other).id);
    }

    @Override
    public String toString() {
        return "br.com.entidades.Sexo[ id=" + id + " ]";
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public List<Pessoa> getPessoas() {
        return pessoas;
    }
    
    
    
    
}
