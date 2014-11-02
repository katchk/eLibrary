package com.katchgr.elibrary.beans;

import com.katchgr.elibrary.entity.User;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class LoginBean {
    private String login;
    private String password;
    private boolean loggedIn;

    @ManagedProperty(value = "#{navigationBean}")
    NavigationBean navigationBean;

    @ManagedProperty(value = "#{appBean}")
    AppBean appBean;

    @ManagedProperty(value = "#{userBean}")
    UserBean userBean;

    public String doSignin(){
        User user = new User(login, password);
        userBean.writeToDB(user);

        return navigationBean.redirectToLogin();
    }

    public String doLogin(){
        User user = new User(login, password);

        if(userBean.isExists(user)){
            loggedIn = true;
            return navigationBean.redirectToWelcome();
        }

        FacesMessage msg = new FacesMessage("Login error!", "ERROR MSG");
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(null, msg);


        return navigationBean.toLogin();
    }

    public String doLogout(){
        loggedIn = false;

        FacesMessage facesMessage = new FacesMessage("Logout success.", "INFO MSG");
        facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);

        return navigationBean.toLogin();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedIn() {
        return true;
    }

    public void setNavigationBean(NavigationBean navigationBean) {
        this.navigationBean = navigationBean;
    }

    public void setAppBean(AppBean appBean) {
        this.appBean = appBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }
}
