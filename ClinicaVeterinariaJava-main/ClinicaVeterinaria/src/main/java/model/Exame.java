package model;

public class Exame {
    private int idExame;
    private String descricao;

    public Exame(int idExame, String descricao) {
        this.idExame = idExame;
        this.descricao = descricao;
    }

    public int getIdExame() {
        return idExame;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
}
