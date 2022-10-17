package main;

import model.AnimalDAO;
import model.ClienteDAO;
import model.EspecieDAO;

public class Main {
    public static void main(String[] args) {        
       
        //ClienteDAO.getInstance().create("Pedro", "Rua Dona Antonia", "13484400", "19996732162", "pedrotrama29@gmail.com");
        System.out.println("------------------CLIENTES---------------------------------");
        System.out.println("------------------Retrieve All Clientes---------------------");
        System.out.println(ClienteDAO.getInstance().retrieveAll());
        System.out.println("------------------Retrieve by id-----------------------------");
        System.out.println(ClienteDAO.getInstance().retrieveById(8));
        System.out.println("------------------ANIMAIS---------------------------------");
        //System.out.println("------------------Create---------------------------------");
        //AnimalDAO.getInstance().create("Leo", 11, "M", 1, "");
        System.out.println("------------------Retrieve All---------------------------------");
        System.out.println(AnimalDAO.getInstance().retrieveAll());
        System.out.println("------------------Retrieve By Id---------------------------------");
        System.out.println(AnimalDAO.getInstance().retrieveById(4));
    }
}