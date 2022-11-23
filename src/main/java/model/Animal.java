package model;
/**
 *
 * @author Pedro Trama
 */
public class Animal {

    private int id;
    private String nome;
    private String anoNasc;
    private String sexo;
    private int id_especie;
    private int id_cliente;

    public Animal(int id, String nome, String anoNasc, String sexo, int id_especie, int id_cliente) {
        this.id = id;
        this.nome = nome;
        this.anoNasc = anoNasc;
        this.sexo = sexo;
        this.id_especie = id_especie;
        this.id_cliente = id_cliente;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setAnoNasc(String anoNasc) {
        this.anoNasc = anoNasc;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setId_especie(int id_especie) {
        this.id_especie = id_especie;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getAnoNasc() {
        return anoNasc;
    }

    public String getSexo() {
        return sexo;
    }

    public int getId_especie() {
        return id_especie;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    @Override
    public String toString() {
        return "Animal{ \nnome: " + nome + "\n}";
    }
}
