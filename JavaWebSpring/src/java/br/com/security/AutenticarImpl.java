/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.security;

import br.com.dao.DAOGeneric;
import br.com.entidades.Pessoa;
import br.com.entidades.User;
import java.util.HashMap;
import org.hibernate.HibernateException;

/**
 *
 * @author 99039833
 */
public class AutenticarImpl implements Autenticavel {

    private static AutenticarImpl autenticacao;
    private User usuario;
    
    private AutenticarImpl() {
        
    }

    static {
        if(autenticacao == null){
            autenticacao = new AutenticarImpl();            
        }          
    }

    @Override
    public HashMap<Boolean, User> autenticarUsuario(String login, String senha) throws HibernateException{
        
        usuario = new User();
        HashMap<Boolean, User> autorizacao = new HashMap<>();
        
        senha = String.valueOf(senha.hashCode());
        String oql = montarQuery(login, senha);        
        
        usuario = DAOGeneric.query(oql, usuario).stream().findAny().orElse(null);
        autorizacao.put(usuario != null, usuario);
        return autorizacao;
    }

    @Override
    public void incluirUsuario(User usuario) throws HibernateException{
        usuario.setSenha(String.valueOf(usuario.getSenha().hashCode()));
        DAOGeneric.salvarOuAtualizar(usuario);
    }
    
    private String montarQuery(String login, String senha) {
        StringBuilder oql = new StringBuilder();
        oql.append("SELECT user")
                .append(" FROM ").append(User.class.getName()).append(" user")
                .append(" WHERE user.login = '").append(login).append("'")
                .append(" AND user.senha = '").append(senha).append("'")
                .append(" AND user.ativo = ").append(true);
        return oql.toString();
    }

    public static AutenticarImpl getInstance() {
        return autenticacao;
    }

}
