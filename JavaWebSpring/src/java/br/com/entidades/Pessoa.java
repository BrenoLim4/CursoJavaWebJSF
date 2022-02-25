/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.entidades;

import br.com.dao.Persistivel;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
public class Pessoa implements Persistivel{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    @Column(name = "sobre_nome",nullable = false)
    private String sobrenome;
    @Column(name = "nome_completo",nullable = false)
    private String nomeCompleto;
    @Column(name = "data_nascimento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
    @JoinColumn(name = "id_sexo", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Sexo sexo;
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "id_pessoa")    
//    private List<User> usuarios = new ArrayList<>();
    @Transient
    private Long idade;
    @Transient
    private Boolean selecione = Boolean.FALSE;
    
    public Pessoa() {
    }
    
    public Pessoa(Long id) {
        this.id = id;
    }

    public Pessoa(long id, String nome, String nomeCompleto, Date dataNascimento) {        
        this.id = id;
        this.nome = nome;
        this.nomeCompleto = nomeCompleto;
        this.dataNascimento = dataNascimento;
    }
    
    @Override
    public Long getIdentifier() {
        return id;
    }
    
    //<editor-fold defaultstate="collapsed" desc=">>>>Equals e HashCode">
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }
    
    @Override
    public boolean equals(Object other) {
        return other instanceof Pessoa && Objects.equals(this.id, ((Pessoa) other).id);
    }
    
    @Override
    public String toString() {
        return "Pessoa{" + "id=" + id + ", nomeCompleto=" + nomeCompleto + '}';
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=">>>>Gets and Sets">
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
    
    public String getNomeCompleto() {
        return nomeCompleto;
    }
    
    public Date getDataNascimento() {
        return dataNascimento;
    }
    
    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    
    public Long getIdade() {
        Calendar cal = Calendar.getInstance();
        long diaMilissegundos = 86400000, agora = cal.getTimeInMillis();
        cal.setTime(dataNascimento);
        long dataNasc = cal.getTimeInMillis();
        idade = ((agora - dataNasc) / diaMilissegundos) / 365;      
        
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
    
    public Boolean getSelecione() {
        return selecione;
    }

    public void setSelecione(Boolean selecione) {
        this.selecione = selecione;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }
    
    public Pessoa getInstance(){
        return new Pessoa();
    }
    
//</editor-fold>


    
}
