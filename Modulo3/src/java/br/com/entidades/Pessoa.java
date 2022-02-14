/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author 99039833
 */
@Entity
@Table(name="pessoa", schema = "modulo3_spring_mvc")
public class Pessoa implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nome;
    @Column(name = "sobre_nome",nullable = false)
    private String sobrenome;
    @Column(name = "nome_completo",nullable = false)
    private String nomeCompleto;
    @Column(name = "data_nascimento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
    @Transient
    private Integer idade = 19;
    
    public Pessoa() {
    }

    public Pessoa(long id, String nome, String nomeCompleto, Date dataNascimento) {        
        this.id = id;
        this.nome = nome;
        this.nomeCompleto = nomeCompleto;
        this.dataNascimento = dataNascimento;
    }

    //<editor-fold defaultstate="collapsed" desc=">>>>Equals e HashCode">
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pessoa other = (Pessoa) obj;
        return this.id == other.id;
    }
    
    @Override
    public String toString() {
        return "Pessoa{" + "id=" + id + ", nomeCompleto=" + nomeCompleto + '}';
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=">>>>Gets and Sets">
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getNomeCompleto() {
        return nomeCompleto;
    }
    
    public Date getDataNascimento() {
        return dataNascimento;
    }
    
    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    
    public Integer getIdade() {
        return idade;
    }
    
    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }
    
    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }
    
//</editor-fold>


    
    
}
