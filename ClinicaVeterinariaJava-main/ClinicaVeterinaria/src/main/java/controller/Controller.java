package controller;

import java.util.List;
import model.Cliente;
import model.ClienteDAO;

public class Controller {
    
    public List<Cliente> getAllClientes(){
        ClienteDAO cliente = new ClienteDAO();
        return cliente.getAllClientes();
    }
    
}
