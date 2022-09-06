package main;

import controller.Controller;
import java.util.Scanner;
import model.Animal;
import model.Cliente;

public class Main {

    public static void main(String[] args) {
       
        Controller controller = new Controller();
        
        System.out.println("Lita de Clientes:\n-----------------");
        
        for(Cliente cliente : controller.getAllClientes()){
            System.out.println(cliente.getId());
            System.out.println(cliente.getNome());
            System.out.println(cliente.getEndereco());
            System.out.println(cliente.getNome());
            System.out.println(cliente.getTelefone());
            System.out.println(cliente.getCep());
            System.out.println(cliente.getEmail());
            System.out.println("-----------------------\nDigite o id do cliente:");
        }
        
        Scanner scan = new Scanner(System.in);
        int id = scan.nextInt();
        switch(id){
            case 1:
                //getAnimal;
                break;
            /*case 2:
                
                break;*/
        }
 
        for(Animal animal : controller.getAllAnimais()){
            System.out.println(animal.getIdAnimal());
            System.out.println(animal.getNome());
            System.out.println(animal.getIdade());
            System.out.println(animal.getSexo());
        }    
    }
}   
