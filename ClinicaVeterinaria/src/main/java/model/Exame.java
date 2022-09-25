package model;

public class Exame {
    private int idExame;
    private String descricao;
    private int idConsulta;
    
    public Exame(int idExame, String descricao, int idConsulta) {
        this.idExame = idExame;
        this.descricao = descricao;
        this.idConsulta = idConsulta;
    }   

    public int getIdExame() {
        return idExame;
    }

    public int getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
}
