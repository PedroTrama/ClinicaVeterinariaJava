package model;
/**
 *
 * @author Pedro Trama
 */
public class Especie {
    private int idEspecie;
    private String nome;
    
    public Especie(int idEspecie, String nome) {
        this.idEspecie = idEspecie;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdEspecie(int idEspecie) {
        this.idEspecie = idEspecie;
    }

    public int getIdEspecie() {
        return idEspecie;
    }    
    
    @Override
    public String toString(){
        return "Espécie{\nid:"+idEspecie+"\nnome: "+nome+"\n}";
    }
    
}
