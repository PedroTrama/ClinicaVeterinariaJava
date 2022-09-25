package model;

public class Consulta {
    private int idConsulta;
    private String data;
    private String hora;
    private int idAnimal;
    private int idVeterinario;
    private int idTratamento;

    public Consulta(int idConsulta, String data, String hora, int idAnimal, int idVeterinario, int idTratamento) {
        this.idConsulta = idConsulta;
        this.data = data;
        this.hora = hora;
        this.idAnimal = idAnimal;
        this.idVeterinario = idVeterinario;
        this.idTratamento = idTratamento;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public int getIdVeterinario() {
        return idVeterinario;
    }

    public void setIdVeterinario(int idVeterinario) {
        this.idVeterinario = idVeterinario;
    }

    public int getIdTratamento() {
        return idTratamento;
    }

    public void setIdTratamento(int idTratamento) {
        this.idTratamento = idTratamento;
    }

    public int getIdConsulta() {
        return idConsulta;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

}
