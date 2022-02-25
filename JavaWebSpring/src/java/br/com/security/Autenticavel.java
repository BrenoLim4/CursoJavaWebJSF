/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.security;

import br.com.entidades.User;
import java.io.Serializable;
import java.util.HashMap;
import org.hibernate.HibernateException;

/**
 *
 * @author 99039833
 */
public interface Autenticavel extends Serializable{
    
    HashMap<Boolean, User> autenticarUsuario(String login, String senha) throws HibernateException;
    
    void incluirUsuario(User usuario) throws HibernateException;
    
}
