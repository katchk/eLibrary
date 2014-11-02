package com.katchgr.elibrary.entity;

import java.io.Serializable;

public class User implements Serializable {
    private String login;
    private String password;
    private String nickName;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(String login, String password, String nickName) {
        this.login = login;
        this.password = password;
        this.nickName = nickName;
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
