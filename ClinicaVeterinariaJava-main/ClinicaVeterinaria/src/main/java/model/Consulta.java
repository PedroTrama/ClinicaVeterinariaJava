package model;

public class Consulta {
    private int idConsulta;
    private String data;
    private String hora;

    public Consulta(int idConsulta, String data, String hora) {
        this.idConsulta = idConsulta;
        this.data = data;
        this.hora = hora;
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
