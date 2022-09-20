package controller;

import java.util.List;
import model.Cliente;
import model.ClienteDAO;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import model.*;

public class Controller {
    
        //Cliente
        public static Cliente addCliente(String nome, String endereco, String telefone,String cep, String email){
            return ClienteDAO.getInstance().create(nome, endereco, telefone, cep, email);
        }
        
        public static boolean isLastClientEmpty(){
            return ClienteDAO.getInstance().isLastEmpty();
        }

        public static List getidLastClient(){
            return ClienteDAO.getInstance().retrieveLast();
        }

        public static List getAllClients(){
            return ClienteDAO.getInstance().retrieveAll();
        }

        public static Cliente getClienteById(int idCliente){
            return ClienteDAO.getInstance().retrieveById(idCliente);
        }
        
        public static List getAllClientsSimilarName(String nome){
            return ClienteDAO.getInstance().retrieveBySimilarName(nome);
        }
}
