package br.com.figestaotcc.homologtccadsfatecipiranga.model;

public class InformacaoUso {

    private String informacaoUsoUid;
    private String informacaoUsoNome;
    private String informacaoUsoDescricao;

    public InformacaoUso() {
    }

    public InformacaoUso(String informacaoUsoUid, String informacaoUsoNome, String informacaoUsoDescricao) {
        this.informacaoUsoUid = informacaoUsoUid;
        this.informacaoUsoNome = informacaoUsoNome;
        this.informacaoUsoDescricao = informacaoUsoDescricao;
    }

    public String getInformacaoUsoUid() {
        return informacaoUsoUid;
    }

    public String getInformacaoUsoNome() {
        return informacaoUsoNome;
    }

    public String getInformacaoUsoDescricao() {
        return informacaoUsoDescricao;
    }
}
