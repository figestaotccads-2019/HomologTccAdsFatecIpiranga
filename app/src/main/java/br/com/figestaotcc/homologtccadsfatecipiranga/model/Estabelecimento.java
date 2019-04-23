package br.com.figestaotcc.homologtccadsfatecipiranga.model;



public class Estabelecimento {

    private String estabelecimentoUid;
    private String estabelecimentoCNPJ;
    private String estabelecimentoRazaoSocial;
    private String estabelecimentoLoja;
    private String estabelecimentoEndereco;

    public Estabelecimento() {
    }

    public Estabelecimento(String estabelecimentoUid, String estabelecimentoCNPJ, String estabelecimentoRazaoSocial, String estabelecimentoLoja, String estabelecimentoEndereco) {
        this.estabelecimentoUid = estabelecimentoUid;
        this.estabelecimentoCNPJ = estabelecimentoCNPJ;
        this.estabelecimentoRazaoSocial = estabelecimentoRazaoSocial;
        this.estabelecimentoLoja = estabelecimentoLoja;
        this.estabelecimentoEndereco = estabelecimentoEndereco;
    }

    public String getEstabelecimentoUid() {
        return estabelecimentoUid;
    }

    public String getEstabelecimentoCNPJ() {
        return estabelecimentoCNPJ;
    }

    public String getEstabelecimentoRazaoSocial() {
        return estabelecimentoRazaoSocial;
    }

    public String getEstabelecimentoLoja() {
        return estabelecimentoLoja;
    }

    public String getEstabelecimentoEndereco() {
        return estabelecimentoEndereco;
    }
}
