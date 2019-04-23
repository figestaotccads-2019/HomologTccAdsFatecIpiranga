package br.com.figestaotcc.homologtccadsfatecipiranga.model;

import android.net.Uri;

import java.io.Serializable;

public class Login implements Serializable {

    private String  loginImage;
    private String  loginUid;
    private String  loginEmail;
    private String  loginStatus;
    private String  loginNome;

    public Login() {
    }

    public Login(String loginImage,String loginUid,  String loginEmail, String loginStatus, String loginNome) {
        this.loginImage = loginImage;
        this.loginUid = loginUid;
        this.loginEmail = loginEmail;
        this.loginStatus = loginStatus;
        this.loginNome = loginNome;
    }

    public String getLoginUid() {
        return loginUid;
    }

    public void setLoginUid(String loginUid) {
        this.loginUid = loginUid;
    }

    public String getLoginImage(String loginImage) {
        return loginImage;
    }

    public void setLoginImage(String loginImage) {
        this.loginImage = loginImage;
    }

    public String getLoginEmail() {
        return loginEmail;
    }

    public void setLoginEmail(String loginEmail) {
        this.loginEmail = loginEmail;
    }

    public String getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(String loginStatus) {
        this.loginStatus = loginStatus;
    }

    public String getLoginNome() {
        return loginNome;
    }

    public void setLoginNome(String loginNome) {
        this.loginNome = loginNome;
    }
}
