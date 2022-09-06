package model;

public class Veterinario {
    private int idVeterinario;
    private String nome;

    public Veterinario(int idVeterinario, String nome) {
        this.idVeterinario = idVeterinario;
        this.nome = nome;
    }

    public int getIdVeterinario() {
        return idVeterinario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
