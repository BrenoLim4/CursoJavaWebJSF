/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bean;

import br.com.entidades.User;
import java.io.Serializable;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
/**
 *
 * @author 99039833
 */
@ApplicationScoped
@ManagedBean(name = "sessionBean", eager = true)
public class SessionBean implements Serializable{

    private final String nameImgDelete = "iconDelete.png";
    private final String nameImageEdit = "iconEdit.png";
    private final String nameImageSave = "iconSave.png";
    private final String nameImageCancel = "iconCancel.png";
    private final String nameImageError = "iconError.png";
    private final String nameImageInfo = "iconInfo.png";
    private final String nameImageUserLogin = "iconUserLogin.png";
    private final String nameImageLogin = "iconLogin.png";
    private final String nameImageLogout = "iconLogout.png";
    private final String URL_PATH_IMG = "/resources/img/";
    
    
    public SessionBean() {
    }
    
    public static User getUser(){
        return (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioSessao");
    }
    
    public String getImageUrl(String nameImg){
        return URL_PATH_IMG + nameImg;
    }

    public String getNameImgDelete() {
        return nameImgDelete;
    }

    public String getNameImageEdit() {
        return nameImageEdit;
    }

    public String getNameImageSave() {
        return nameImageSave;
    }

    public String getNameImageCancel() {
        return nameImageCancel;
    }

    public String getNameImageError() {
        return nameImageError;
    }

    public String getNameImageInfo() {
        return nameImageInfo;
    }

    public String getNameImageUserLogin() {
        return nameImageUserLogin;
    }

    public String getNameImageLogin() {
        return nameImageLogin;
    }

    public String getNameImageLogout() {
        return nameImageLogout;
    }
    
    
    
}
