/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bean;

import br.com.entidades.User;
import br.com.security.AutenticarImpl;
import java.io.Serializable;
import java.util.HashMap;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.HibernateException;

/**
 *
 * @author 99039833
 */
@ManagedBean(name = "login")
@ViewScoped
public class LoginBackBean implements Serializable {

    private String campoLogin;
    private String campoSenha;
    private User usuario = new User();
    private StringBuilder oql;
    private HashMap<Boolean, User> validacao;

    public LoginBackBean() {
    }

    public String logar() {
        String url = "";
        try {
            validacao = AutenticarImpl.getInstance().autenticarUsuario(campoLogin, campoSenha);
            if (validacao.containsKey(Boolean.TRUE)) {
                usuario = validacao.get(Boolean.TRUE);
                FacesContext.getCurrentInstance()
                        .getExternalContext()
                        .getSessionMap()
                        .put("usuarioSessao", usuario);
                url = "/faces/index.xhtml?faces-rediretc=true";
            } else {
                FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login ou senha inválidos", ""));
            }
        } catch (HibernateException ex) {
            System.err.println(ex.toString());
        } catch (Exception ex) {
            System.err.println(ex.toString());
        } finally {
            limparCampos();
            return url;
        }
    }

    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();

        context.getExternalContext()
                .getSessionMap()
                .remove("usuarioSessao");
        HttpServletRequest http = (HttpServletRequest) context.getExternalContext().getRequest();
        http.getSession().invalidate();

        return "/faces/login.xhtml?faces-rediretc=true";
    }

    private void limparCampos() {
        campoLogin = null;
        campoSenha = null;
    }

    public String getCampoLogin() {
        return campoLogin;
    }

    public void setCampoLogin(String campoLogin) {
        this.campoLogin = campoLogin;
    }

    public String getCampoSenha() {
        return campoSenha;
    }

    public void setCampoSenha(String campoSenha) {
        this.campoSenha = campoSenha;
    }

}
