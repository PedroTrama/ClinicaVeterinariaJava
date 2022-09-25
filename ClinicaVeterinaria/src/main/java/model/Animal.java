package model;

public class Animal {
    private int idAnimal;
    private String nome;
    private int idade;
    private String sexo;
    private int idEspecie;
    private int idCliente;

    public Animal(int idAnimal, String nome, int idade, String sexo, int idEspecie, int idCliente) {
        this.idAnimal = idAnimal;
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
        this.idEspecie = idEspecie;
        this.idCliente = idCliente;
    }

    public int getIdEspecie() {
        return idEspecie;
    }

    public void setIdEspecie(int idEspecie) {
        this.idEspecie = idEspecie;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdCliente() {
        return idCliente;
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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    
    @Override
    public String toString() {
        return "Animal{" + "nome=" + nome + '}';
    }
    
}
    

