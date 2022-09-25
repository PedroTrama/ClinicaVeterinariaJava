package model;

public class Tratamento {
    private int idTratamento;
    private String dataInicio;
    private String dataFim;
    private int idAnimal;

    public Tratamento(int idTratamento, String dataInicio, String dataFim, int idAnimal) {
        this.idTratamento = idTratamento;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.idAnimal = idAnimal;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public int getIdTratamento() {
        return idTratamento;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }
    
}
