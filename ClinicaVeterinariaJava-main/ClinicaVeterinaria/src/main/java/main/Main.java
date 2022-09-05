package main;

import controller.Controller;
import model.Cliente;

public class Main {

    public static void main(String[] args) {
       
        Controller controller = new Controller();
        
        for(Cliente cliente : controller.getAllClientes()){
            System.out.println(cliente.getId());
            System.out.println(cliente.getNome());
            System.out.println(cliente.getEndereco());
            System.out.println(cliente.getNome());
            System.out.println(cliente.getTelefone());
            System.out.println(cliente.getCep());
            System.out.println(cliente.getEmail());
        }
    }
}   
