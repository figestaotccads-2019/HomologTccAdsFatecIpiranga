package br.com.figestaotcc.homologtccadsfatecipiranga.model;

import android.media.Image;

import java.io.Serializable;

public class Perfil implements Serializable {

    public static final String KEY_LOGIN_UID = "loginUid";

    private String loginUid;
    private String perfilUid;
    private String  perfilImage;
    private String perfilEmail;
    private String perfilStatus;
    private String perfilNome;
    private String perfilEndereco;
    private String perfilCidade;
    private String perfilEstado;
    private String perfilCep;
    private String perfilLocalizacao;
    private String perfilTelefone;

    public Perfil() {

    }

    public Perfil(String loginUid, String perfilUid, String perfilImage,String perfilEmail, String perfilStatus, String perfilNome, String perfilEndereco, String perfilCidade, String perfilEstado, String perfilCep, String perfilLocalizacao, String perfilTelefone) {
        this.loginUid = loginUid;
        this.perfilUid = perfilUid;
        this.perfilImage = perfilImage;
        this.perfilEmail = perfilEmail;
        this.perfilStatus = perfilStatus;
        this.perfilNome = perfilNome;
        this.perfilEndereco = perfilEndereco;
        this.perfilCidade = perfilCidade;
        this.perfilEstado = perfilEstado;
        this.perfilCep = perfilCep;
        this.perfilLocalizacao = perfilLocalizacao;
        this.perfilTelefone = perfilTelefone;
    }

    public String getLoginUid() {
        return loginUid;
    }

    public String getPerfilUid() {
        return perfilUid;
    }

    public String getPerfilImage() {
        return perfilImage;
    }

    public String getPerfilEmail() {
        return perfilEmail;
    }

    public String getPerfilStatus() {
        return perfilStatus;
    }

    public String getPerfilNome() {
        return perfilNome;
    }

    public String getPerfilEndereco() {
        return perfilEndereco;
    }

    public String getPerfilCidade() {
        return perfilCidade;
    }

    public String getPerfilEstado() {
        return perfilEstado;
    }

    public String getPerfilCep() {
        return perfilCep;
    }

    public String getPerfilLocalizacao() {
        return perfilLocalizacao;
    }

    public String getPerfilTelefone() {
        return perfilTelefone;
    }

    public void setLoginUid(String loginUid) {
        this.loginUid = loginUid;
    }

    public String setPerfilUid(String perfilUid) {
        this.perfilUid = perfilUid;
        return perfilUid;
    }

    public void setPerfilImage(String perfilImage) {
        this.perfilImage = perfilImage;
    }

    public void setPerfilEmail(String perfilEmail) {
        this.perfilEmail = perfilEmail;
    }

    public void setPerfilStatus(String perfilStatus) {
        this.perfilStatus = perfilStatus;
    }

    public void setPerfilNome(String perfilNome) {
        this.perfilNome = perfilNome;
    }

    public void setPerfilEndereco(String perfilEndereco) {
        this.perfilEndereco = perfilEndereco;
    }

    public void setPerfilCidade(String perfilCidade) {
        this.perfilCidade = perfilCidade;
    }

    public void setPerfilEstado(String perfilEstado) {
        this.perfilEstado = perfilEstado;
    }

    public void setPerfilCep(String perfilCep) {
        this.perfilCep = perfilCep;
    }

    public void setPerfilLocalizacao(String perfilLocalizacao) {
        this.perfilLocalizacao = perfilLocalizacao;
    }

    public void setPerfilTelefone(String perfilTelefone) {
        this.perfilTelefone = perfilTelefone;
    }


    }

