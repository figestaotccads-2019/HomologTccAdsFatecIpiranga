package br.com.figestaotcc.homologtccadsfatecipiranga.model;

public class ListaCompras {

    private String listaComprasUid;
    private String listaComprasNome;
    private String listaComprasDescricao;

    public ListaCompras() {
    }

    public ListaCompras(String listaComprasUid, String listaComprasNome, String listaComprasDescricao) {
        this.listaComprasUid = listaComprasUid;
        this.listaComprasNome = listaComprasNome;
        this.listaComprasDescricao = listaComprasDescricao;
    }

    public String getListaComprasUid() {
        return listaComprasUid;
    }

    public String getListaComprasNome() {
        return listaComprasNome;
    }

    public String getListaComprasDescricao() {
        return listaComprasDescricao;
    }
}
