package br.com.figestaotcc.homologtccadsfatecipiranga.model;

import java.util.Date;

public class Oferta {

    private String ofertaUid;
    private Date ofertaData;
    private Float ofertaValor;
    private String ofertaStatus;
    private String ofertaDescricao;

    public Oferta() {
    }

    public Oferta(String ofertaUid, Date ofertaData, Float ofertaValor, String ofertaStatus, String ofertaDescricao) {
        this.ofertaUid = ofertaUid;
        this.ofertaData = ofertaData;
        this.ofertaValor = ofertaValor;
        this.ofertaStatus = ofertaStatus;
        this.ofertaDescricao = ofertaDescricao;
    }

    public String getOfertaUid() {
        return ofertaUid;
    }

    public Date getOfertaData() {
        return ofertaData;
    }

    public Float getOfertaValor() {
        return ofertaValor;
    }

    public String getOfertaStatus() {
        return ofertaStatus;
    }

    public String getOfertaDescricao() {
        return ofertaDescricao;
    }
}
