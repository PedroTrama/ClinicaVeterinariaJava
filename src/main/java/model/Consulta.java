package model;

import java.util.Calendar;
/**
 *
 * @author Pedro Trama
 */
public class Consulta {

    private int idConsulta;
    private int idTratamento;
    private int idVeterinario;
    private int idAnimal;
    private Calendar dataConsulta;
    private String hora;
    private String comentario;
    private boolean terminou;

    public Consulta(int idConsulta, int idTratamento, int idVeterinario, int idAnimal, Calendar dataConsulta, String hora, String comentario, boolean terminou) {
        this.idConsulta = idConsulta;
        this.idTratamento = idTratamento;
        this.idVeterinario = idVeterinario;
        this.idAnimal = idAnimal;
        this.dataConsulta = dataConsulta;
        this.hora = hora;
        this.comentario = comentario;
        this.terminou = terminou;
    }

    public int getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    public int getIdTratamento() {
        return idTratamento;
    }

    public void setIdTratamento(int idTratamento) {
        this.idTratamento = idTratamento;
    }

    public int getIdVeterinario() {
        return idVeterinario;
    }

    public void setIdVeterinario(int idVeterinario) {
        this.idVeterinario = idVeterinario;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public Calendar getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(Calendar dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public boolean isTerminou() {
        return terminou;
    }

    public void setTerminou(boolean terminou) {
        this.terminou = terminou;
    }

    @Override
    public String toString() {
        return "Consulta{\nCódigo Consulta: " + idConsulta
                + "\nid Tratamento: " + idTratamento
                + "\nid Veterinário: " + idVeterinario
                + "\nid Animal: " + idAnimal
                + "\nComentários: " + comentario
                + "\nData: " + dataConsulta
                + "\nHorário: " + hora
                + "\nStatus: " + (terminou == true ? "Realizada" : "Marcada")
                + "\n}";
    }

}
