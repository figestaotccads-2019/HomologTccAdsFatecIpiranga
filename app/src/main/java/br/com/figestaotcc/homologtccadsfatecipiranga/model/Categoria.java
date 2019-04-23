package br.com.figestaotcc.homologtccadsfatecipiranga.model;

public class Categoria {

    private String  categoriaUid;
    private String  categoriaPerfilUid;
    private String  categoriaTipo;
    private String  categoriaNome;
    private String  categoriaDescricao;
    private String  categoriaImage;



    public Categoria() {
    }


    public Categoria(String categoriaUid,String  categoriaPerfilUid, String categoriaNome,String categoriaImage, String categoriaDescricao, String categoriaTipo) {
        this.categoriaUid = categoriaUid;
        this.categoriaPerfilUid = categoriaPerfilUid;
        this.categoriaNome = categoriaNome;
        this.categoriaImage = categoriaImage;
        this.categoriaDescricao = categoriaDescricao;
        this.categoriaTipo = categoriaTipo;
    }

    public String getCategoriaUid() {
        return categoriaUid;
    }

    public String getCategoriaImage() {
        return categoriaImage;
    }

    public String getCategoriaDescricao() {
        return categoriaDescricao;
    }

    public String getCategoriaTipo() {
        return categoriaTipo;
    }

    public void setCategoriaUid(String categoriaUid) {
        this.categoriaUid = categoriaUid;
    }

    public void setCategoriaImage(String categoriaImage) {
        this.categoriaImage = categoriaImage;
    }

    public void setCategoriaDescricao(String categoriaDescricao) {
        this.categoriaDescricao = categoriaDescricao;
    }

    public void setCategoriaTipo(String categoriaTipo) {
        this.categoriaTipo = categoriaTipo;
    }

    public String getCategoriaNome() {
        return categoriaNome;
    }

    public void setCategoriaNome(String categoriaNome) {
        this.categoriaNome = categoriaNome;
    }

    public String getCategoriaPerfilUid() {
        return categoriaPerfilUid;
    }

    public void setCategoriaPerfilUid(String categoriaPerfilUid) {
        this.categoriaPerfilUid = categoriaPerfilUid;
    }
}
