package controller;

import java.util.List;
import model.Animal;
import model.AnimalDAO;
import model.Cliente;
import model.ClienteDAO;

public class Controller {
    
    public List<Cliente> getAllClientes(){
        ClienteDAO cliente = new ClienteDAO();
        return cliente.getAllClientes();
    }
     public List<Animal> getAllAnimais(){
        AnimalDAO animal = new AnimalDAO();
        return animal.getAllAnimais();
    }
}
