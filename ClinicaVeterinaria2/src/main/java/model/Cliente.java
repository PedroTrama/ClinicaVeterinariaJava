package model;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private int idCliente;
    private String nome;
    private String endereco;
    private String telefone;
    private String cep;
    private String email;
    
    private List<Animal> animais;
    
    public Cliente(int id, String nome, String endereco, String telefone, String cep, String email) {
        this.idCliente = id;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.cep = cep;
        this.email = email;
        this.animais = new ArrayList<Animal>();
    }

    public int getId() {
        return idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(!email.equals("")){
            this.email = email;
        }
    }
    
    public boolean addAnimal(Animal animal){
        if(!animal.getNome().isBlank()){
            animais.add(animal);
            return true;
        }
        return false;
    }
    
    public List<Animal> getAnimais(){
        List<Animal> copia = new ArrayList<Animal>(animais);        
        return copia;
    }
    
    @Override
    public String toString() {        
        String desc = "Cliente{" + "nome=" + nome + ", endereco=" + endereco + ", telefone=" + telefone + ", cep=" + cep + ", email=" + email + '}';
        String strAnimais = animais.toString();
        return desc + "\n" + strAnimais;
    }    

    public int getIdCliente() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}