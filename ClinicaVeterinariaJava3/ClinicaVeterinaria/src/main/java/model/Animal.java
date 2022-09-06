package model;

public class Animal {
    private int idAnimal;
    private String nome;
    private int idade;
    private char sexo;

    public Animal(int idAnimal, String nome, int idade, char sexo) {
        this.idAnimal = idAnimal;
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }
    
}
