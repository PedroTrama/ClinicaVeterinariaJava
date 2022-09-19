package controller;

import java.util.List;
import model.Cliente;
import model.ClienteDAO;

public class Controller {
    
        //Cliente
        public static Cliente addCliente(String nome, String endereco, String telefone,String cep, String email){
            return ClienteDAO.getInstance().create(nome, endereco, telefone, cep, email);
        }
        
        public static boolean isLastClientEmpty(){
            return ClienteDAO.getIstance().isLastEmpty();
        }

        public static List getiLastClient(){
            return ClienteDAO.getIstance().retrieveLast();
        }

        public static List getAllClients(){
            return ClienteDAO.getIstance().retrieveAll();
        }

        public static boolean getClientById(int id){
            return ClienteDAO.getIstance().retrieveById();
        }
        
        public static boolean isLastClientEmpty(){
            return ClienteDAO.getIstance().isLastEmpty();
        }
}
