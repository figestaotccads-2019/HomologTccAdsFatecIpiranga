package br.com.figestaotcc.homologtccadsfatecipiranga.model;

import android.media.Image;

public class Produto {

    private String produtoUid;
    private String produtoCodBarras;
    private String produtoNome;
    private Image produtoImage;

    public Produto() {
    }

    public Produto(String produtoUid, String produtoCodBarras, String produtoNome, Image produtoImage) {
        this.produtoUid = produtoUid;
        this.produtoCodBarras = produtoCodBarras;
        this.produtoNome = produtoNome;
        this.produtoImage = produtoImage;
    }

    public String getProdutoUid() {
        return produtoUid;
    }

    public String getProdutoCodBarras() {
        return produtoCodBarras;
    }

    public String getProdutoNome() {
        return produtoNome;
    }

    public Image getProdutoImage() {
        return produtoImage;
    }
}
